package bean;

import entidades.Categoria;
import entidades.Produto;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import util.JpaUtil;
import util.Upload;

@ManagedBean
@RequestScoped
public class ProdutoBean {

    private Produto produto = new Produto();
    private List<Categoria> categorias = new ArrayList<>();


    public ProdutoBean() {
        carregaCategoria();
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

        System.out.println(event.getFile().getContents());
        this.produto.setFoto(event.getFile().getContents());

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

}
