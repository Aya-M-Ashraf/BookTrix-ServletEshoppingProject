package Beans;

import java.util.Date;
import java.util.HashMap;


public class Cart {
    private int pending,cartId;
    private Date creationDate;
    private HashMap<Book,Integer> myBooks;
    private User user;
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public HashMap<Book, Integer> getMyBooks() {
        return myBooks;
    }

    public void setMyBooks(HashMap<Book, Integer> myBooks) {
        this.myBooks = myBooks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
