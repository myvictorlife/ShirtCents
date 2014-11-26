package bean;

import entidades.Usuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import util.JpaUtil;

@ManagedBean
@SessionScoped
public class UsuarioLogin {
    
    private Usuario usuario = new Usuario();

    public UsuarioLogin() {
    }
    
    public void sair() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null) {
            session.invalidate();
        }    
        usuario = new Usuario();
    }
    
    public void entrar(){
        EntityManager manager = null;
        try {
            manager = JpaUtil.getEntityManager();  //acesso ao banco

            List<Usuario> usuarios = manager.createQuery("Select u from Usuario u where u.login = :login and u.senha = :senha")
                    .setParameter("login", usuario.getLogin())
                    .setParameter("senha", usuario.getSenha())
                    .getResultList();

            if (usuarios != null && usuarios.isEmpty()) {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", " usuario inexistente com tal user!"));
               // return null;
            }
            usuario = usuarios.get(0);
            
       //     System.out.println("Nome: " + usuario.getNome());
         //   System.out.println("Email: " + usuario.getEmail());
           // System.out.println("Profile: " + usuario.getProfile());

            //return "login"; //vai para a página principal.xhtml
        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()));
        } finally {
            JpaUtil.closeEntityManager(manager); //fecha acesso ao banco
        }
        
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Senha: " + usuario.getSenha());
        System.out.println("Profile: " + usuario.getProfile());
        //return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
