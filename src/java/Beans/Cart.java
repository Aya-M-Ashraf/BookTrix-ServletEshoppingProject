
package Beans;

import java.util.Date;
import java.util.Vector;


public class Cart {
    private int quantity,pending,cartId;
    private Date creationDate;
    private Vector<Book> myBooks;
    private User user;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPending() {
        return pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
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
