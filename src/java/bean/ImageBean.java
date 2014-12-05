package bean;

import entidades.Produto;
import java.io.ByteArrayInputStream;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import util.JpaUtil;

@ManagedBean(name = "imgBean")
@ApplicationScoped
public class ImageBean {

    public StreamedContent getImage() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }

        String id = fc.getExternalContext().getRequestParameterMap().get("photo_id");
        byte[] photoData = findUserPhoto(Integer.parseInt(id)).getFoto();
        return new DefaultStreamedContent(new ByteArrayInputStream(photoData));
    }

    public Produto findUserPhoto(Integer id) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            return em.find(Produto.class, id);
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", e.getMessage()));

        } finally {
            JpaUtil.closeEntityManager(em);
        }
        return null;
    }

}
