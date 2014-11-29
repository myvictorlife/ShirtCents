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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.apache.commons.io.IOUtils;
import util.JpaUtil;


@ManagedBean
@RequestScoped
public class ProdutoBean {

    private Produto produto ;
    private List<Categoria> categorias = new ArrayList<>();


    public ProdutoBean() {
        produto = new Produto();
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

}
