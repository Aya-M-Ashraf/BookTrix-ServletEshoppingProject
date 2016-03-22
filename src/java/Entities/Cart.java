/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findByUserName", query = "SELECT c FROM Cart c WHERE c.userName = :userName"),
    @NamedQuery(name = "Cart.findByCreationDate", query = "SELECT c FROM Cart c WHERE c.creationDate = :creationDate"),
    @NamedQuery(name = "Cart.findByTotal", query = "SELECT c FROM Cart c WHERE c.total = :total"),
    @NamedQuery(name = "Cart.findByPending", query = "SELECT c FROM Cart c WHERE c.pending = :pending"),
    @NamedQuery(name = "Cart.findByCartId", query = "SELECT c FROM Cart c WHERE c.cartId = :cartId")})
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "total")
    private Integer total;
    @Column(name = "pending")
    private Integer pending;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cart_id")
    private Integer cartId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private Collection<CartBook> cartBookCollection;

    public Cart() {
    }

    public Cart(Integer cartId) {
        this.cartId = cartId;
    }

    public Cart(Integer cartId, String userName) {
        this.cartId = cartId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @XmlTransient
    public Collection<CartBook> getCartBookCollection() {
        return cartBookCollection;
    }

    public void setCartBookCollection(Collection<CartBook> cartBookCollection) {
        this.cartBookCollection = cartBookCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartId != null ? cartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.cartId == null && other.cartId != null) || (this.cartId != null && !this.cartId.equals(other.cartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Cart[ cartId=" + cartId + " ]";
    }
    
}
