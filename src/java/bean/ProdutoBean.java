package bean;

import entidades.Categoria;
import entidades.Produto;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import util.JpaUtil;

@ManagedBean
@SessionScoped
public class ProdutoBean {

    private Produto produto = new Produto();
    private List<Produto> produtos = new ArrayList<>();
    private List<Produto> produtosBusca = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();
    private String buscaProduto = "";
    private PieChartModel pieModel = new PieChartModel();
    private BarChartModel barModel = new BarChartModel();

    public ProdutoBean() {
        carregaCategoria();
        buscaProdutoPorNome();
        listarProdutoGrafico();
        createBarModel();
    }
    
    public void init(){
        createBarModel();
    }
    
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
    
     public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
 
    public List<Produto> getProdutosBusca() {
        return produtosBusca;
    }

    public void setProdutosBusca(List<Produto> produtosBusca) {
        this.produtosBusca = produtosBusca;
    }

    public String getBuscaProduto() {
        return buscaProduto;
    }

    public void setBuscaProduto(String buscaProduto) {
        this.buscaProduto = buscaProduto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    private void carregaCategoria() {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            this.categorias = em.createNamedQuery("Categoria.findAll").getResultList();

            JpaUtil.closeEntityManager(em);
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", e.getMessage()));
        }
    }

    public void modificaOpcaoRadio() {
        if (produto.getSexo().equals(String.valueOf(0))) {
            produto.setSexo("Masculino");
        } else if (produto.getSexo().equals(String.valueOf(1))) {
            produto.setSexo("Feminino");
        } else {
            produto.setSexo("Unisex");
        }
    }

    public void salvar() {
        modificaOpcaoRadio();
        salvarProduto();
        novo();
    }

    public void upload(FileUploadEvent event) {

        try {
            this.produto.setFoto(IOUtils.toByteArray(event.getFile().getInputstream()));
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK! ", event.getFile().getFileName() + new java.util.Date()));

        } catch (IOException ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    private void salvarProduto() {
        EntityManager em = null;
        EntityTransaction emTx = null;
        try {
            em = JpaUtil.getEntityManager();
            emTx = em.getTransaction();
            emTx.begin();
            this.produto = em.merge(this.produto);

            emTx.commit();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK! ", "Produto salvo com sucesso! " + new java.util.Date()));

        } catch (Exception ex) {
            try {
                emTx.rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()));
        } finally {
            JpaUtil.closeEntityManager(em);
        }
        novo();
    }

    public void novo() {
        this.produto = new Produto();
    }

    public void todosProdutos() {
        produtos = new ArrayList<>();
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            this.produtos = em.createNamedQuery("Produto.findAll").getResultList();

        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", e.getMessage()));

        } finally {
            JpaUtil.closeEntityManager(em);
        }
    }

    public String buscaTop(){
        buscaProdutoPorNome();
        return "busca-produto";
    }
    public void buscaProdutoPorNome() {

        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();

            produtosBusca = em.createQuery("Select p from Produto p where UPPER(p.descricao) like UPPER(:descricao)")
                    .setParameter("descricao", "%"+buscaProduto+"%")
                    .getResultList();


        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", e.getMessage()));

        } finally {
            JpaUtil.closeEntityManager(em);
        }

    }

    public void listarProdutoGrafico(){
        todosProdutos();
        graficar();
    }
    public void graficar(){
        pieModel = new PieChartModel();
        try {
            
        
       for (Produto prod : this.produtos ) {
          pieModel.set(prod.getDescricao(), prod.getPrecoVenda());
       }

        pieModel.setTitle("Preços");
        pieModel.setLegendPosition("e");
        pieModel.setFill(false);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(150);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
