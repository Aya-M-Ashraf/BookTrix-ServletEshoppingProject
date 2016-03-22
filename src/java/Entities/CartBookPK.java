/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ahmed
 */
@Embeddable
public class CartBookPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "cart_id")
    private int cartId;
    @Basic(optional = false)
    @Column(name = "book_id")
    private int bookId;

    public CartBookPK() {
    }

    public CartBookPK(int cartId, int bookId) {
        this.cartId = cartId;
        this.bookId = bookId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cartId;
        hash += (int) bookId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartBookPK)) {
            return false;
        }
        CartBookPK other = (CartBookPK) object;
        if (this.cartId != other.cartId) {
            return false;
        }
        if (this.bookId != other.bookId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.CartBookPK[ cartId=" + cartId + ", bookId=" + bookId + " ]";
    }
    
}
