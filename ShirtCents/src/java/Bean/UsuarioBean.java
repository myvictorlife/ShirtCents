package Bean;

import Entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import util.JpaUtil;

@ManagedBean
@SessionScoped
public class UsuarioBean {
    
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios = new ArrayList<>();
    
    public UsuarioBean() {
        listarTodos();
    }
    
    public void novo(){
        usuario = new Usuario();
    }
    
    private void listarTodos() {
        EntityManager manager = null;
        try {
            manager = JpaUtil.getEntityManager(); //equivale a uma conexão

            usuarios = manager
                    .createQuery("Select usua from Usuario usua order by usua.id", Usuario.class)
                    .getResultList();

        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()));
        } finally {
            JpaUtil.closeEntityManager(manager);
        }
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    
    
    
    
}
