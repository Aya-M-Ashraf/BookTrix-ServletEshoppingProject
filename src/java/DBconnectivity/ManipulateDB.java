package DBconnectivity;

import Beans.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManipulateDB {

    Connection connection;

    public ManipulateDB() {
        connection = DBconnection.getConnection();
    }

    public Vector<User> selectAllUsers() {
        Vector<User> allUsers = new Vector<>();
        try {
            Statement statement = connection.createStatement();
            System.out.println("asdasdasd");
            String queryString = "select * from user";
            ResultSet resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                User user = new User();
                user.setEmail(resultSet.getString(1));
                user.setUserName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setCreditLimit(resultSet.getDouble(4));
                user.setJob(resultSet.getString(5));
                user.setAddress(resultSet.getString(6));
                user.setProfilePicUrl(resultSet.getString(7));
                user.setRole(resultSet.getString(8));
                allUsers.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    public Vector<Book> selectAllBooks() {
        Vector<Book> allBooks = new Vector<>();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select b.book_id,b.book_name,b.quantitiy,b.author,b.category_id,b.price,b.img,c.category_name,b.description from book b join category c on b.category_id=c.category_id";
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt(1));
                book.setBookName(resultSet.getString(2));
                book.setQuantity(resultSet.getInt(3));
                book.setAuthor(resultSet.getString(4));
                book.setPrice(resultSet.getInt(6));
                book.setImg(resultSet.getString(7));
                int categoryId = resultSet.getInt(5);
                String categoryName = resultSet.getString(8);
                book.setDescription(resultSet.getString(9));
                Category category = new Category(categoryId, categoryName);
                book.setCategory(category);
                allBooks.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allBooks;
    }

    public Vector<Category> selectAllCategories() {
        Vector<Category> allCategories = new Vector<>();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select * from category ";
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));

                allCategories.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allCategories;
    }

    public User selectUserByEmail(String userEmail) {
        User user = new User();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select * from user where email='" + userEmail + "'";
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                user.setEmail(resultSet.getString(1));
                user.setUserName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setCreditLimit(resultSet.getDouble(4));
                user.setJob(resultSet.getString(5));
                user.setAddress(resultSet.getString(6));
                user.setProfilePicUrl(resultSet.getString(7));
                user.setRole(resultSet.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User selectUserByUserName(String userName) {
        User user = new User();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select * from user where user_name='" + userName + "'";
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                user.setEmail(resultSet.getString(1));
                user.setUserName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setCreditLimit(resultSet.getDouble(4));
                user.setJob(resultSet.getString(5));
                user.setAddress(resultSet.getString(6));
                user.setProfilePicUrl(resultSet.getString(7));
                user.setRole(resultSet.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public Book selectBookById(int bookId) {
        Book book = new Book();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select b.book_id,b.book_name,b.quantitiy,b.author,b.price,b.img,b.category_id,c.category_name,b.description from book b join category c on b.category_id=c.category_id where b.book_id=" + bookId;
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                book.setBookId(resultSet.getInt(1));
                book.setBookName(resultSet.getString(2));
                book.setQuantity(resultSet.getInt(3));
                book.setAuthor(resultSet.getString(4));
                book.setPrice(resultSet.getInt(5));
                book.setImg(resultSet.getString(6));
                int categoryId = resultSet.getInt(7);
                String categoryName = resultSet.getString(8);
                book.setDescription(resultSet.getString(9));
                Category category = new Category(categoryId, categoryName);
                book.setCategory(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }

    public Cart selectCartById(int cartId) {
        Cart cart = new Cart();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select * from cart where cart_id=" + cartId;
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                cart.setCreationDate(resultSet.getDate(2));
                cart.setQuantity(resultSet.getInt(3));
                cart.setPending(resultSet.getInt(4));
                cart.setCartId(resultSet.getInt(5));
                String userEmail = resultSet.getString(1);
                Statement statement2 = connection.createStatement();
                String queryString2 = "select * from user where email='" + userEmail + "'";
                ResultSet resultSet2 = statement2.executeQuery(queryString2);
                User user = new User();
                while (resultSet2.next()) {
                    user.setEmail(resultSet.getString(1));
                    user.setUserName(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setCreditLimit(resultSet.getDouble(4));
                    user.setJob(resultSet.getString(5));
                    user.setAddress(resultSet.getString(6));
                    user.setProfilePicUrl(resultSet.getString(7));
                    user.setRole(resultSet.getString(8));
                }
                cart.setUser(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    public boolean insertUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String st = "insert into user values('" + user.getEmail() + "','" + user.getUserName() + "','" + user.getPassword() + "'," + user.getCreditLimit() + ",'" + user.getJob() + "','" + user.getAddress() + "','" + user.getProfilePicUrl() + "','" + user.getRole() + "')";
            statement.executeUpdate(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertBook(Book book) {
        try {
            Statement statement = connection.createStatement();
            String st = "insert into book (`book_name`, `quantitiy`, `author`, `category_id`, `price`, `img`, `description`) values('" + book.getBookName() + "'," + book.getQuantity() + ",'" + book.getAuthor() + "'," + book.getCategory().getId() + "," + book.getPrice() + ",'" + book.getImg() + "','" + book.getDescription() + "')";
            statement.executeUpdate(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertCategory(Category category) {
        try {
            Statement statement = connection.createStatement();
            String st = "insert into category values(" + category.getId() + ",'" + category.getName() + "')";
            statement.executeUpdate(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean insertCart(Cart cart) {
        try {
            Statement statement = connection.createStatement();
            String st = "insert into cart values('" + cart.getUser().getUserName() + "','" + cart.getCreationDate() + "'," + cart.getQuantity() + "," + cart.getPending() + "," + cart.getCartId() + ")";
            statement.executeUpdate(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String selectRoleFromUser(String userName, String password) {
        String role = null;
        try {
            Statement statement = connection.createStatement();
            String queryString = "select role from user where user_name='" + userName + "'" + "and password='" + password + "'";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            role = "not found";
        }
        return role;
    }

    public Category selectCategoryFromName(String categoryName) {
        Category category = new Category();
        try {
            Statement statement = connection.createStatement();
            String queryString = "select *  from category where category_name='" + categoryName + "'";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                category.setId(rs.getInt(1));
                category.setName(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return category;
    }

    public boolean checkUserNameExistence(String userName) {
        boolean userNameFound = false;
        try {
            Statement statement = connection.createStatement();
            String queryString = "select * from user where user_name = '" + userName + "'";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                userNameFound = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            userNameFound = false;
        }
        return userNameFound;
    }
    
    public boolean checkEmailExistence(String email){
        boolean emailFound=false;
        try {
            Statement statement = connection.createStatement();
            String queryString = "select * from user where email = '"+email+"'";
                ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                emailFound =true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            emailFound = false;
        }
        return emailFound;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int selectPendingCartIdFromCart(String userName) {

        try {
            int cartId = -1;
            Statement statement1 = connection.createStatement();
            String queryString1 = "select cart_id from cart where pending = '1' and user_name= '" + userName + "'";
            ResultSet resultSet = statement1.executeQuery(queryString1);
            if (resultSet.next()) {
                cartId = resultSet.getInt(1);
            }
            return cartId;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public boolean insertBookIntoCart(int bookId, int cartId) {
        try {
            Statement statement = connection.createStatement();
            String st = "insert into cart_book values('" + cartId + "','" + bookId + "')";
            statement.executeUpdate(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Vector<Book> selectBooksFromCart(int cartId) {
        Vector<Book> books = new Vector<>();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select book_id from cart_book where cart_id= '" + cartId + "'";
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                int bookId = resultSet.getInt(1);
                books.add(selectBookById(bookId));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return books;
    }
    
    public boolean editUserData(User user){
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "update user set password='"+user.getPassword()+"',credit_Limit="+user.getCreditLimit()+",job='"+user.getJob()+"',address='"+user.getAddress()+"',photo='"+user.getProfilePicUrl()+"' where email='"+user.getEmail()+"'";
            statement1.executeUpdate(queryString1);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
