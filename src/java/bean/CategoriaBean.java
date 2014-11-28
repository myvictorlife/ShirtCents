package bean;

import entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import net.bootsfaces.component.NavBarLinks;
import util.JpaUtil;

@ManagedBean
@RequestScoped
public class CategoriaBean {

    private Categoria categoria = new Categoria();
    private List<Categoria> categorias = new ArrayList<>();
    

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public CategoriaBean() {
        carregaCategoria();
    }

    public void carregaCategoria() {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            this.categorias = em.createNamedQuery("Categoria.findAll").getResultList();
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", e.getMessage()));

        } finally {
            JpaUtil.closeEntityManager(em);
        }
    }

    public void salvar() {
        salvarProduto();
        carregaCategoria();
    }

    public void salvarProduto() {
        EntityManager em = null;
        EntityTransaction emTx = null;
        try {
            em = JpaUtil.getEntityManager();
            emTx = em.getTransaction();
            emTx.begin();
            this.categoria = em.merge(this.categoria);
            emTx.commit();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK! ", "Categoria salva com sucesso! " + new java.util.Date()));
            novo();
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
    }

    public void novo() {
        this.categoria = new Categoria();
    }

    public void apagar() {
        apagarCategoria();
        carregaCategoria();
        novo();
    }

    public void apagarCategoria() {
        EntityManager manager = null;
        EntityTransaction etx = null;
        try {
            manager = JpaUtil.getEntityManager(); //equivale a uma conexão
            etx = manager.getTransaction();
            etx.begin();

            this.categoria = manager.find(Categoria.class, this.categoria.getIdCategoria());
            manager.remove(this.categoria);

            etx.commit();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK! ", "Categoria apagada com sucesso! " + new java.util.Date()));
        } catch (Exception ex) {
            try {
                etx.rollback();
            } catch (Exception ex2) { /* nada aui por hora */            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()));
        } finally {
            JpaUtil.closeEntityManager(manager);
        }
    }
    
}
