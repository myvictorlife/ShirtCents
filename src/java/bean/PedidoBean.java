package bean;

import entidades.Itens;
import entidades.Pedido;
import entidades.Produto;
import entidades.Usuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import util.JpaUtil;

@ManagedBean
@SessionScoped
public class PedidoBean {

    private Pedido pedido = new Pedido();
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    private Produto produtoSelecionado = new Produto();
    private Usuario usuarioSelecionado = new Usuario();

    public PedidoBean() {
    }

    public void selecionaUsuario() {
        pedido.setIdUsuario(usuarioSelecionado);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "Cliente selecionado!"));
    }

    public void adicionarItem() {
        Itens item = new Itens();
        item.setPedido(pedido);
        item.setProduto(produtoSelecionado);
        item.setQuantidade(1);
        item.setValor(produtoSelecionado.getPrecoVenda() * item.getQuantidade());
        if (pedido.getItensList() == null) {
            pedido.setItensList(new ArrayList<Itens>());
        }
        pedido.getItensList().add(item);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "Item " + produtoSelecionado.getDescricao() + " adicionado ao carrinho!"));
    }

    public void salvar() {
        if (salvarPedido()) {
            pedido = new Pedido();
            usuarioSelecionado = new Usuario();
        }
    }

    private void totalizaItens() {
        Double total = 0.0;

        if (pedido.getItensList() == null) {
            pedido.setItensList(new ArrayList<Itens>());
        }
        for (Itens item : pedido.getItensList()) {
            total = total + item.getValor();
        }
        pedido.setTotal(total);
    }

    private boolean salvarPedido() {
        EntityManager manager = null;
        EntityTransaction etx = null;
        try {
            manager = JpaUtil.getEntityManager(); //equivale a uma conexão
            etx = manager.getTransaction();
            etx.begin();

            UsuarioLogin usuarioLogin = (UsuarioLogin) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("UsuarioLogin");
            Usuario usuarioLogado = usuarioLogin.getUsuario();
            pedido.setIdUsuario(usuarioLogado);
            pedido.setDataPed(new java.util.Date());
            manager.persist(pedido);

            for (Itens item : pedido.getItensList()) {
                manager.persist(item);
            }

            etx.commit();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK! ", "Pedido salvo com sucesso! " + new java.util.Date()));
            return true;
        } catch (Exception ex) {
            try {
                etx.rollback();
            } catch (Exception ex2) { /* nada aui por hora */            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: ", ex.getMessage()));
        } finally {
            JpaUtil.closeEntityManager(manager);
        }
        return false;
    }

    public List<Itens> getItens() {
        totalizaItens();
        return pedido.getItensList();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

}
