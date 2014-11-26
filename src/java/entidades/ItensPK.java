/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author victor
 */
@Embeddable
public class ItensPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PRODUTO")
    private int idProduto;
    @Basic(optional = false)
    @Column(name = "PEDIDO_ID")
    private int pedidoId;

    public ItensPK() {
    }

    public ItensPK(int idProduto, int pedidoId) {
        this.idProduto = idProduto;
        this.pedidoId = pedidoId;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProduto;
        hash += (int) pedidoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensPK)) {
            return false;
        }
        ItensPK other = (ItensPK) object;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        if (this.pedidoId != other.pedidoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ItensPK[ idProduto=" + idProduto + ", pedidoId=" + pedidoId + " ]";
    }
    
}
