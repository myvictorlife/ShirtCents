/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author victor
 */
@Entity
@Table(name = "ITENS")
@NamedQueries({
    @NamedQuery(name = "Itens.findAll", query = "SELECT i FROM Itens i"),
    @NamedQuery(name = "Itens.findByQuantidade", query = "SELECT i FROM Itens i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "Itens.findByValorUnitario", query = "SELECT i FROM Itens i WHERE i.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "Itens.findByIdProduto", query = "SELECT i FROM Itens i WHERE i.itensPK.idProduto = :idProduto"),
    @NamedQuery(name = "Itens.findByPedidoId", query = "SELECT i FROM Itens i WHERE i.itensPK.pedidoId = :pedidoId")})
public class Itens implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItensPK itensPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUANTIDADE")
    private Double quantidade;
    @Column(name = "VALOR_UNITARIO")
    private Double valorUnitario;
    @JoinColumn(name = "PEDIDO_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "ID_PRODUTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public Itens() {
    }

    public Itens(ItensPK itensPK) {
        this.itensPK = itensPK;
    }

    public Itens(int idProduto, int pedidoId) {
        this.itensPK = new ItensPK(idProduto, pedidoId);
    }

    public ItensPK getItensPK() {
        return itensPK;
    }

    public void setItensPK(ItensPK itensPK) {
        this.itensPK = itensPK;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itensPK != null ? itensPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itens)) {
            return false;
        }
        Itens other = (Itens) object;
        if ((this.itensPK == null && other.itensPK != null) || (this.itensPK != null && !this.itensPK.equals(other.itensPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Itens[ itensPK=" + itensPK + " ]";
    }
    
}
