package bean;

import entidades.Formapagamento;
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
public class FormaPagamentoBean {

        
        private Formapagamento pagamento = new Formapagamento();
        private List<Formapagamento> pagamentos = new ArrayList<>();

         public FormaPagamentoBean() {
             listarTodos();
         }
         
         private void listarTodos() {
        EntityManager manager = null;
        try {
            manager = JpaUtil.getEntityManager(); //equivale a uma conexão

            pagamentos = manager
                    .createQuery("Select usua from Usuario usua order by usua.id", Formapagamento.class)
                    .getResultList();

        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()));
        } finally {
            JpaUtil.closeEntityManager(manager);
        }

    }
        
        public void novo() {
            pagamento = new Formapagamento();
        }
        

        public Formapagamento getPagamento() {
            return pagamento;
        }

        public void setPagamento(Formapagamento pagamento) {
            this.pagamento = pagamento;
        }

        public List<Formapagamento> getPagamentos() {
            return pagamentos;
        }

        public void setPagamentos(List<Formapagamento> pagamentos) {
            this.pagamentos = pagamentos;
        }
        
        
    
}
