/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author victor
 */
@Entity
@Table(name = "PEDIDO")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM Pedido p WHERE p.id = :id"),
    @NamedQuery(name = "Pedido.findByPagamentoPedido", query = "SELECT p FROM Pedido p WHERE p.pagamentoPedido = :pagamentoPedido"),
    @NamedQuery(name = "Pedido.findByStatusPedido", query = "SELECT p FROM Pedido p WHERE p.statusPedido = :statusPedido"),
    @NamedQuery(name = "Pedido.findByCriadoPedido", query = "SELECT p FROM Pedido p WHERE p.criadoPedido = :criadoPedido"),
    @NamedQuery(name = "Pedido.findByModificadoPedido", query = "SELECT p FROM Pedido p WHERE p.modificadoPedido = :modificadoPedido")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PAGAMENTO_PEDIDO")
    private Double pagamentoPedido;
    @Column(name = "STATUS_PEDIDO")
    private String statusPedido;
    @Column(name = "CRIADO_PEDIDO")
    @Temporal(TemporalType.DATE)
    private Date criadoPedido;
    @Column(name = "MODIFICADO_PEDIDO")
    @Temporal(TemporalType.DATE)
    private Date modificadoPedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Collection<Itens> itensCollection;
    @JoinColumn(name = "CLIENTE_ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Cliente clienteIdCliente;
    @JoinColumn(name = "FORMAPAGAMENTO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Formapagamento formapagamentoId;

    public Pedido() {
    }

    public Pedido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPagamentoPedido() {
        return pagamentoPedido;
    }

    public void setPagamentoPedido(Double pagamentoPedido) {
        this.pagamentoPedido = pagamentoPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Date getCriadoPedido() {
        return criadoPedido;
    }

    public void setCriadoPedido(Date criadoPedido) {
        this.criadoPedido = criadoPedido;
    }

    public Date getModificadoPedido() {
        return modificadoPedido;
    }

    public void setModificadoPedido(Date modificadoPedido) {
        this.modificadoPedido = modificadoPedido;
    }

    public Collection<Itens> getItensCollection() {
        return itensCollection;
    }

    public void setItensCollection(Collection<Itens> itensCollection) {
        this.itensCollection = itensCollection;
    }

    public Cliente getClienteIdCliente() {
        return clienteIdCliente;
    }

    public void setClienteIdCliente(Cliente clienteIdCliente) {
        this.clienteIdCliente = clienteIdCliente;
    }

    public Formapagamento getFormapagamentoId() {
        return formapagamentoId;
    }

    public void setFormapagamentoId(Formapagamento formapagamentoId) {
        this.formapagamentoId = formapagamentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pedido[ id=" + id + " ]";
    }
    
}
