package controllers;

import Beans.Book;
import Beans.Cart;
import Beans.User;
import DBconnectivity.ManipulateDB;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
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

    public boolean addBookToCart(String userName, int bookId, int bookQuantity) {
        int cartId = manipulateDB.selectPendingCartIdFromCart(userName);
        if (cartId == -1) {                 //no pending cart for this user is found
            Cart cart = new Cart();
            cart.setCreationDate(new java.sql.Date(new java.util.Date().getTime()));
            cart.setUser(manipulateDB.selectUserByUserName(userName));
            cart.setPending(1);
            manipulateDB.insertCart(cart);
            return manipulateDB.insertBookIntoCart(bookId, bookQuantity, manipulateDB.selectPendingCartIdFromCart(userName));
        } else {
            if (manipulateDB.selectBookIdFromCart(cartId, bookId)) {
                return manipulateDB.increaseBookQuantityInCartByOne(cartId, bookId);
            } else {
                return manipulateDB.insertBookIntoCart(bookId, bookQuantity, cartId);
            }
        }

    }

    public HashMap<Book, Integer> getAllBooksInCart(String userName) {
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

    public boolean buyMyCart(String userName) {
        double totalCartCost = 0;
        int cartId = manipulateDB.selectPendingCartIdFromCart(userName);
        System.out.println(cartId);
        Cart cart = manipulateDB.selectCartById(cartId);
        cart.setPending(0);

        for (Map.Entry<Book, Integer> book : cart.getMyBooks().entrySet()) {
            totalCartCost += ((book.getKey().getPrice()) * book.getValue());
        }
        System.out.println(totalCartCost);
        System.out.println(cart.getUser().getCreditLimit());
        if (totalCartCost <= cart.getUser().getCreditLimit()) // customer can afford the cart
        {
            cart.getUser().setCreditLimit(cart.getUser().getCreditLimit() - totalCartCost);
            cart.setTotal(totalCartCost);
            System.out.println(totalCartCost);
            manipulateDB.updateCart(cart);
            manipulateDB.editUserData(cart.getUser());
            for (Map.Entry<Book, Integer> book : cart.getMyBooks().entrySet()) {
                Book eachBook = book.getKey();
                eachBook.setQuantity((eachBook.getQuantity()) - book.getValue());
                manipulateDB.updateBook(eachBook);
            }
            return true;
        } else {                               // customer can't afford the cart
            return false;
        }
    }

    public Vector<Book> getAllBooks() {
        return manipulateDB.selectAllBooks();
    }

    public boolean addCategory(String categoryName) {
        return manipulateDB.insertCategory(categoryName);
    }

    public boolean deleteBookById(int bookId) {
        return manipulateDB.deleteBookById(bookId);
    }

    public void updateBookCountInCart(String userName, int bookId, int value) {
        int cartID = manipulateDB.selectPendingCartIdFromCart(userName);
        System.out.println(cartID + " " + bookId + " " + value);
        manipulateDB.updateBookCountInCart(cartID, bookId, value);

    }

    public User getUserByUserName(String userName) {
        return manipulateDB.selectUserByUserName(userName);
    }

    public void updateBookInfo(Book book) {

        manipulateDB.updateAllBookInfo(book);

    }

    public double selectTotalPriceOfBooks() {
        ManipulateDB manipulateDB = new ManipulateDB();
        double totalPriceOfBooks = 0;
        Vector<Book> allBooks = manipulateDB.selectAllBooks();
        for (Book book : allBooks) {
            totalPriceOfBooks += book.getPrice();
        }
        return totalPriceOfBooks;
    }
}
