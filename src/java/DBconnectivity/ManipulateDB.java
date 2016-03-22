package DBconnectivity;

import Beans.*;
import dao.BookJpaController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManipulateDB {

    Connection connection;

    public ManipulateDB() {
        connection = DBconnection.getConnection();
    }
//1
    public Vector<User> selectAllUsers() {
        Vector<User> allUsers = new Vector<>();
        try {
            Statement statement = connection.createStatement();
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
//2
    public Vector<Book> selectAllBooks() {
        Vector<Book> allBooks = new Vector<>();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select b.book_id,b.book_name,b.quantity,b.author,b.category_id,b.price,b.img,c.category_name,b.description from book b join category c on b.category_id=c.category_id";
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
//3
    public Book selectSingleBook(int bookId) {
        Vector<Book> allBooks = new Vector<>();
        Book book = new Book();

        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select b.book_id,b.book_name,b.quantity,b.author,b.category_id,b.price,b.img,c.category_name,b.description from book b join category c on b.category_id=c.category_id where b.book_id ='" + bookId + "'";
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }
//4
    public Vector<Book> selectAllBooksWhereNameLike(String bookName) {
        Vector<Book> allBooks = new Vector<>();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select b.book_id,b.book_name,b.quantity,b.author,b.category_id,b.price,b.img,c.category_name,b.description from book b join category c on b.category_id=c.category_id where b.book_name like'%" + bookName + "%'";
            System.out.println("in select search books " + queryString1);
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
//5
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
//6
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
//7
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
//8
    public Book selectBookById(int bookId) {
        Book book = new Book();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select b.book_id,b.book_name,b.quantity,b.author,b.price,b.img,b.category_id,c.category_name,b.description from book b join category c on b.category_id=c.category_id where b.book_id=" + bookId;
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
//9
    public boolean deleteBookById(int bookId) {
        try {
            Statement statement1 = connection.createStatement();
            String query2 = "delete from cart_book where book_id =" + bookId;
            statement1.executeUpdate(query2);
            String queryString1 = "delete from book  where book_id=" + bookId;
            statement1.executeUpdate(queryString1);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
//10
    public Cart selectCartById(int cartId) {
        Cart cart = new Cart();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select * from cart where cart_id=" + cartId;
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                cart.setCreationDate(resultSet.getDate(2));
                cart.setTotal(resultSet.getInt(3));
                cart.setPending(resultSet.getInt(4));
                cart.setCartId(resultSet.getInt(5));
                String userName = resultSet.getString(1);
                Statement statement2 = connection.createStatement();
                String queryString2 = "select * from user where user_name='" + userName + "'";
                ResultSet resultSet2 = statement2.executeQuery(queryString2);
                User user = new User();
                while (resultSet2.next()) {
                    user.setEmail(resultSet2.getString(1));
                    user.setUserName(resultSet2.getString(2));
                    user.setPassword(resultSet2.getString(3));
                    user.setCreditLimit(resultSet2.getDouble(4));
                    user.setJob(resultSet2.getString(5));
                    user.setAddress(resultSet2.getString(6));
                    user.setProfilePicUrl(resultSet2.getString(7));
                    user.setRole(resultSet2.getString(8));
                }
                cart.setUser(user);
            }
            cart.setMyBooks(selectBooksWithQuantitiesFromCart(cartId));
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }
//11
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
//12
    public boolean insertBook(Book book) {
        try {
            Statement statement = connection.createStatement();
            String st = "insert into book (book_name, quantity, author, category_id, price, img, description) values ('" + book.getBookName() + "'," + book.getQuantity() + ",'" + book.getAuthor() + "'," + book.getCategory().getId() + "," + book.getPrice() + ",'" + book.getImg() + "','" + book.getDescription() + "')";
            statement.executeUpdate(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
//13
    public boolean insertCategory(String categoryName) {
        try {
            Statement statement = connection.createStatement();
            boolean isNewCategory = true;
            for (Category c : selectAllCategories()) {
                if (c.getName().equals(categoryName)) {
                    isNewCategory = false;
                }
            }
            if (isNewCategory) {
                String st = "insert into category (category_name) values('" + categoryName + "')";
                statement.executeUpdate(st);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
//14
    public boolean insertCart(Cart cart) {
        try {
            Statement statement = connection.createStatement();
            String st = "insert into cart values('" + cart.getUser().getUserName() + "','" + cart.getCreationDate() + "'," + cart.getTotal() + "," + cart.getPending() + "," + cart.getCartId() + ")";
            statement.executeUpdate(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
//15
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
//16
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
//17
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
//18
    public boolean checkEmailExistence(String email) {
        boolean emailFound = false;
        try {
            Statement statement = connection.createStatement();
            String queryString = "select * from user where email = '" + email + "'";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                emailFound = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            emailFound = false;
        }
        return emailFound;
    }
//19
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//20
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
//21
    public boolean insertBookIntoCart(int bookId, int bookQuantity, int cartId) {
        try {
            Statement statement = connection.createStatement();
            String st = "insert into cart_book values('" + cartId + "','" + bookId + "','" + bookQuantity + "')";
            statement.executeUpdate(st);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
//22
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
//23
    public HashMap<Book, Integer> selectBooksWithQuantitiesFromCart(int cartId) {
        HashMap<Book, Integer> booksWithQuantity = new HashMap<>();
        try {

            Statement statement1 = connection.createStatement();
            String queryString1 = "select book_quantity, book_id from cart_book where cart_id= '" + cartId + "' order by book_id";
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                int bookQuantity = resultSet.getInt(1);
                int bookId = resultSet.getInt(2);
                booksWithQuantity.put(selectBookById(bookId), bookQuantity);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);

        }
        return booksWithQuantity;
    }
//24
    public boolean editUserData(User user) {
        if (user.getProfilePicUrl() != null) {
            try {
                Statement statement1 = connection.createStatement();
                String queryString1 = "update user set password='" + user.getPassword() + "',credit_Limit=" + user.getCreditLimit() + ",job='" + user.getJob() + "',address='" + user.getAddress() + "',photo='" + user.getProfilePicUrl() + "' where email='" + user.getEmail() + "'";
                statement1.executeUpdate(queryString1);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            try {
                Statement statement1 = connection.createStatement();
                String queryString1 = "update user set password='" + user.getPassword() + "',credit_Limit=" + user.getCreditLimit() + ",job='" + user.getJob() + "',address='" + user.getAddress() + "'where email='" + user.getEmail() + "'";
                statement1.executeUpdate(queryString1);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }
//25
    public boolean deleteBook(String userName, int bookId) {
        try {
            Statement statement = connection.createStatement();
            String query1 = "select cart_id from cart where user_name =  '" + userName + "'";
            ResultSet resultSet = statement.executeQuery(query1);
            if (resultSet.next()) {
                int c_id = resultSet.getInt(1);
                String query2 = "delete from cart_book where book_id ='" + bookId + "' and cart_id=" + c_id;
                int result = statement.executeUpdate(query2);

                if (result == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
//26
    public Vector<Book> selectAllBooksInCategory(String categoryName) {
        Vector<Book> books = new Vector<>();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select category_id from category where category_name = '" + categoryName + "'";
            ResultSet resultSet1 = statement1.executeQuery(queryString1);
            while (resultSet1.next()) {
                int category_id = resultSet1.getInt(1);
                Category category = new Category(category_id, categoryName);
                Statement statement = connection.createStatement();
                String queryString = "select * from book where category_id = " + category_id;
                ResultSet resultSet = statement.executeQuery(queryString);
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setCategory(category);
                    book.setBookId(resultSet.getInt(1));
                    book.setBookName(resultSet.getString(2));
                    book.setQuantity(resultSet.getInt(3));
                    book.setAuthor(resultSet.getString(4));
                    book.setPrice(resultSet.getInt(6));
                    book.setImg(resultSet.getString(7));
                    book.setDescription(resultSet.getString(8));
                    books.add(book);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }
//27
    public void updateCart(Cart cart) {
        try {
            PreparedStatement statment = connection.prepareStatement("update cart set pending = ? , total = ? where cart_id = ?");
            statment.setInt(1, cart.getPending());
            statment.setDouble(2, cart.getTotal());
            statment.setInt(3, cart.getCartId());

            statment.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//28
    public void updateBook(Book book) {
        try {
            PreparedStatement statment = connection.prepareStatement("update book set quantity = ? where book_id = ?");
            statment.setInt(1, book.getQuantity());
            statment.setInt(2, book.getBookId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//29
    public void updateAllBookInfo(Book book) {
        try {
            PreparedStatement statment = connection.prepareStatement("UPDATE book SET quantity=?, author=?, price=?, description=? WHERE book_id= ?");
            statment.setInt(1, book.getQuantity());
            statment.setString(2, book.getAuthor());
            statment.setInt(3, book.getPrice());
            statment.setString(4, book.getDescription());
            statment.setInt(5, book.getBookId());
            statment.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//30
    public boolean selectBookIdFromCart(int cartId, int bookId) {

        try {
            PreparedStatement statment = connection.prepareStatement("select book_id from cart_book  where cart_id = ? and book_id=?");
            statment.setInt(1, cartId);
            statment.setInt(2, bookId);
            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
//31
    public boolean increaseBookQuantityInCartByOne(int cartId, int bookId) {
        try {
            PreparedStatement sta = connection.prepareStatement("select book_quantity from  cart_book where cart_id = ? and  book_id = ?");
            sta.setInt(1, cartId);
            sta.setInt(2, bookId);
            ResultSet rrs = sta.executeQuery();
            if (rrs.next()) {
                int quantity = rrs.getInt(1);
                quantity = quantity + 1;

                PreparedStatement statment = connection.prepareStatement("update cart_book set book_quantity = ? where book_id = ?");
                statment.setInt(1, quantity);
                statment.setInt(2, bookId);
                if (statment.executeUpdate() == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
//32
    public boolean updateBookCountInCart(int cartID, int bookId, int value) {
        try {

            PreparedStatement statment = connection.prepareStatement("update cart_book set book_quantity = ? where book_id = ? and cart_id = ?");
            statment.setInt(1, value);
            statment.setInt(2, bookId);
            statment.setInt(3, cartID);
            if (statment.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
//33
    public Vector<Cart> selectAllPastCarts(String userName) {
        int cartId = 0;
        Cart cart = new Cart();
        Vector<Cart> pastCarts = new Vector<>();
        try {
            Statement statement1 = connection.createStatement();
            String queryString1 = "select cart_id from cart where pending = '0' and user_name= '" + userName + "'";
            ResultSet resultSet = statement1.executeQuery(queryString1);
            while (resultSet.next()) {
                cartId = resultSet.getInt(1);
                cart = selectCartById(cartId);
                pastCarts.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pastCarts;
    }
}
