package controllers;

import Beans.Book;
import Beans.Cart;
import Beans.User;
import DBconnectivity.ManipulateDB;
import java.sql.Date;
import java.util.Vector;

public class ControlServlet {

    ManipulateDB manipulateDB;

    public ControlServlet() {
        manipulateDB = new ManipulateDB();
    }

    public String checkLogin(String username, String password) {
        String userRole = manipulateDB.selectRoleFromUser(username, password);
        return userRole;
    }

    public boolean doesUserNameExist(String userName) {
        return manipulateDB.checkUserNameExistence(userName);
    }

    public boolean addBookToCart(String userName, int bookId) {
        int cartId = manipulateDB.selectPendingCartIdFromCart(userName);
        System.out.println(cartId);
        if (cartId == -1) {                 //no pending cart for this user is found
            Cart cart = new Cart();
            cart.setCreationDate(new Date(876236576587L));
            cart.setUser(manipulateDB.selectUserByUserName(userName));
            cart.setPending(1);
            return manipulateDB.insertCart(cart);
        } else {
            return manipulateDB.insertBookIntoCart(bookId, cartId);
        }

    }

    public Vector<Book> getAllBooksInCart(String userName) {
        int cartId = manipulateDB.selectPendingCartIdFromCart(userName);
        return manipulateDB.selectBooksFromCart(cartId);
    }
    
     public boolean doesEmailExist(String email){
        return manipulateDB.checkEmailExistence(email);
    } 
     
    public boolean editUserDate(User user){
        return manipulateDB.editUserData(user);
    }
}
