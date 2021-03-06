package Beans;
// Generated Mar 22, 2016 10:27:26 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cart generated by hbm2java
 */
public class Cart  implements java.io.Serializable {


     private Integer cartId;
     private String userName;
     private Date creationDate;
     private Integer total;
     private Integer pending;
     private Set cartBooks = new HashSet(0);

    public Cart() {
    }

	
    public Cart(String userName) {
        this.userName = userName;
    }
    public Cart(String userName, Date creationDate, Integer total, Integer pending, Set cartBooks) {
       this.userName = userName;
       this.creationDate = creationDate;
       this.total = total;
       this.pending = pending;
       this.cartBooks = cartBooks;
    }
   
    public Integer getCartId() {
        return this.cartId;
    }
    
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Date getCreationDate() {
        return this.creationDate;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public Integer getTotal() {
        return this.total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Integer getPending() {
        return this.pending;
    }
    
    public void setPending(Integer pending) {
        this.pending = pending;
    }
    public Set getCartBooks() {
        return this.cartBooks;
    }
    
    public void setCartBooks(Set cartBooks) {
        this.cartBooks = cartBooks;
    }
}
