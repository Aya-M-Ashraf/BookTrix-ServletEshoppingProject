/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "cart_book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CartBook.findAll", query = "SELECT c FROM CartBook c"),
    @NamedQuery(name = "CartBook.findByCartId", query = "SELECT c FROM CartBook c WHERE c.cartBookPK.cartId = :cartId"),
    @NamedQuery(name = "CartBook.findByBookId", query = "SELECT c FROM CartBook c WHERE c.cartBookPK.bookId = :bookId"),
    @NamedQuery(name = "CartBook.findByBookQuantity", query = "SELECT c FROM CartBook c WHERE c.bookQuantity = :bookQuantity")})
public class CartBook implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CartBookPK cartBookPK;
    @Basic(optional = false)
    @Column(name = "book_quantity")
    private String bookQuantity;
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Book book;
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cart cart;

    public CartBook() {
    }

    public CartBook(CartBookPK cartBookPK) {
        this.cartBookPK = cartBookPK;
    }

    public CartBook(CartBookPK cartBookPK, String bookQuantity) {
        this.cartBookPK = cartBookPK;
        this.bookQuantity = bookQuantity;
    }

    public CartBook(int cartId, int bookId) {
        this.cartBookPK = new CartBookPK(cartId, bookId);
    }

    public CartBookPK getCartBookPK() {
        return cartBookPK;
    }

    public void setCartBookPK(CartBookPK cartBookPK) {
        this.cartBookPK = cartBookPK;
    }

    public String getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(String bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartBookPK != null ? cartBookPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartBook)) {
            return false;
        }
        CartBook other = (CartBook) object;
        if ((this.cartBookPK == null && other.cartBookPK != null) || (this.cartBookPK != null && !this.cartBookPK.equals(other.cartBookPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.CartBook[ cartBookPK=" + cartBookPK + " ]";
    }
    
}
