package Beans;
// Generated Mar 22, 2016 10:27:26 PM by Hibernate Tools 4.3.1



/**
 * CartBookId generated by hbm2java
 */
public class CartBookId  implements java.io.Serializable {


     private int cartId;
     private int bookId;

    public CartBookId() {
    }

    public CartBookId(int cartId, int bookId) {
       this.cartId = cartId;
       this.bookId = bookId;
    }
   
    public int getCartId() {
        return this.cartId;
    }
    
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
    public int getBookId() {
        return this.bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CartBookId) ) return false;
		 CartBookId castOther = ( CartBookId ) other; 
         
		 return (this.getCartId()==castOther.getCartId())
 && (this.getBookId()==castOther.getBookId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCartId();
         result = 37 * result + this.getBookId();
         return result;
   }   


}

