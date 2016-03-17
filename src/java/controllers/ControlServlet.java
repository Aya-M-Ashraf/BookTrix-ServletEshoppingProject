package controllers;

import Beans.Book;
import Beans.Cart;
import Beans.User;
import DBconnectivity.ManipulateDB;
import java.sql.Date;
import java.util.HashMap;
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

    public boolean addBookToCart(String userName, int bookId , int bookQuantity) {
        int cartId = manipulateDB.selectPendingCartIdFromCart(userName);
        if (cartId == -1) {                 //no pending cart for this user is found
            Cart cart = new Cart();
            cart.setCreationDate(new Date(876236576587L));
            cart.setUser(manipulateDB.selectUserByUserName(userName));
            cart.setPending(1);
            manipulateDB.insertCart(cart);
            return manipulateDB.insertBookIntoCart(bookId, bookQuantity,manipulateDB.selectPendingCartIdFromCart(userName) );
        } else {
            return manipulateDB.insertBookIntoCart(bookId, bookQuantity, cartId);
        }

    }

    public HashMap<Book,Integer> getAllBooksInCart(String userName) {
        int cartId = manipulateDB.selectPendingCartIdFromCart(userName);
        return manipulateDB.selectBooksWithQuantitiesFromCart(cartId);
    }

    public boolean doesEmailExist(String email) {
        return manipulateDB.checkEmailExistence(email);
    }

    public boolean editUserDate(User user) {
        return manipulateDB.editUserData(user);
    }

    public User getUser(String userName) {
        return manipulateDB.selectUserByUserName(userName);
    }

    public Vector<Book> getBooksInCategory(String categoryName) {
        return manipulateDB.selectAllBooksInCategory(categoryName);
    }
    
    public boolean buyMyCart(String userName){
        double totalCartCost = 0;
         int cartId = manipulateDB.selectPendingCartIdFromCart(userName);
         Cart cart = manipulateDB.selectCartById(cartId);
         cart.setPending(0);
         for(Book book:cart.getMyBooks()){
             totalCartCost =+ book.getPrice();
         }
         if (totalCartCost <= cart.getUser().getCreditLimit()) // customer can afford the cart
         {
             cart.getUser().setCreditLimit(cart.getUser().getCreditLimit()- totalCartCost);
             manipulateDB.updateCart(cart);
             manipulateDB.editUserData(cart.getUser());
             return true;
         }else{    // customer can't afford the cart
             return false;
         }
    }
    public Vector<Book> getAllBooks(){
        return manipulateDB.selectAllBooks();
    }
}
