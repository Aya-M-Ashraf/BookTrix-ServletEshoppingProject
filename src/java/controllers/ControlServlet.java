package controllers;

import Beans.Cart;
import DBconnectivity.ManipulateDB;
import java.util.Date;


public class ControlServlet {
    ManipulateDB manipulateDB;
    public ControlServlet(){
            manipulateDB= new ManipulateDB();  
    }

    public String checkLogin(String username, String password){
        String userRole = manipulateDB.selectRoleFromUser(username, password);
        return userRole;
    }
    public boolean doesUserNameExist(String userName){
        return manipulateDB.checkUserNameExistence(userName);
    }

    public boolean addBookToCart(String userName,int bookId) {
       int cartId  = manipulateDB.selectPendingCartIdFromCart(userName);
       if(cartId==-1){ //no pending cart for this user is found
       Cart cart = new Cart();
       cart.setCreationDate(new Date());
       cart.setUser(manipulateDB.selectUserByUserName(userName));
       }else{
//           manipulateDB.insertBookIntoCart(bookId,cartId);
       } 
       return false; //to be continued
    }
}
