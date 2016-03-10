/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author lenovo
 */
public class Cart {
    private int quantity,purchased,cartId;
    private Date creationDate;
    private Vector<Book> myBooks;
    private User user;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Vector<Book> getMyBooks() {
        return myBooks;
    }

    public void setMyBooks(Vector<Book> myBooks) {
        this.myBooks = myBooks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
