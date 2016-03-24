package DBconnectivity;

import Beans.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class ManipulateDB {

    SessionFactory sessionFactory;
    Session session;

    public ManipulateDB() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

//1
    public Vector<User> selectAllUsers() {

        Criteria myCriteria = session.createCriteria(User.class);
        List result = myCriteria.list();
        Vector<User> allUsers = new Vector<>(result);
        return allUsers;
    }
//2

    public Vector<Book> selectAllBooks() {
        Criteria criteria = session.createCriteria(Book.class);
        List result = criteria.list();
        Vector<Book> allBooks = new Vector<>(result);
        return allBooks;
    }
//3

    public Book selectSingleBook(int bookId) {
        return ((Book) session.get(Book.class, bookId));
    }
//4

    public Vector<Book> selectAllBooksWhereNameLike(String bookName) {
        Criteria criteria = session.createCriteria(Book.class).add(Restrictions.like("bookName", bookName, MatchMode.ANYWHERE));
        List result = criteria.list();
        Vector<Book> allBooks = new Vector<>(result);
        return allBooks;
    }
//5

    public Vector<Category> selectAllCategories() {

        Criteria myCriteria = session.createCriteria(Category.class);
        List result = myCriteria.list();
        Vector<Category> allCategories = new Vector<>(result);

        return allCategories;
    }

//6
    public User selectUserByEmail(String userEmail) {
        Criteria myCriteria = session.createCriteria(User.class).add(Restrictions.eq("email", userEmail));
        User user = (User) myCriteria.uniqueResult();
        return user;
    }

//7
    public User selectUserByUserName(String userName) {
        return ((User) session
                .createCriteria(User.class)
                .add(Restrictions.eq("userName", userName))
                .uniqueResult());
    }
//8

    public Book selectBookById(int bookId) {

        Criteria myCriteria = session.createCriteria(Book.class).add(Restrictions.eq("bookId", bookId));
        Book book = (Book) myCriteria.uniqueResult();
        return book;
    }

//9 
    public boolean deleteBookById(int bookId) {

        List<CartBook> cartBook = (List<CartBook>) session.createCriteria(CartBook.class)
                .add(Restrictions.eq("book.bookId", bookId)).list();
        for (Iterator iterator = cartBook.iterator(); iterator.hasNext();) {
            CartBook c = (CartBook) iterator.next();
//            Object persistance = session.load(CartBook.class, c.getId());
            session.delete(c);
        }
        Criteria myCriteria = session.createCriteria(Book.class);
        Book book = (Book) myCriteria.add(Restrictions.eq("bookId", bookId)).uniqueResult();
        session.delete(book);
        return true;

    }
//10

    public Cart selectCartById(int cartId) {
        Criteria criteria = session.createCriteria(Cart.class).add(Restrictions.eq("cartId", cartId));
        Cart cart = (Cart) criteria.uniqueResult();
        return cart;
    }
//11

    public boolean insertUser(User user) {
        session.save(user);
        session.getTransaction().commit();
        return true;
    }
//12

    public boolean insertBook(Book book) {
        session.save(book);
        session.getTransaction().commit();
        return true;
    }
//13

    public boolean insertCategory(String categoryName) {
        boolean isNewCategory = true;
        for (Category c : selectAllCategories()) {
            if (c.getCategoryName().equals(categoryName)) {
                isNewCategory = false;
            }
        }
        if (isNewCategory) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            session.save(category);
            session.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

//14
    public boolean insertCart(Cart cart) {
        session.save(cart);
        session.getTransaction().commit();
        return true;
    }
//15

    public String selectRoleFromUser(String userName, String password) {
        String role = null;
        Criteria myCriteria = session.createCriteria(User.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("password", password));
        User user = (User) myCriteria.uniqueResult();
        role = user.getRole();
        return role;
    }
//16

    public Category selectCategoryFromName(String categoryName) {
        return ((Category) session
                .createCriteria(Category.class)
                .add(Restrictions.eq("categoryName", categoryName))
                .uniqueResult());
    }
//17

    public boolean checkUserNameExistence(String userName) {
        User u = (User) session
                .createCriteria(User.class)
                .add(Restrictions.eq("userName", userName))
                .uniqueResult();

        if (u != null) {
            return true;
        } else {
            return false;
        }
    }
//18

    public boolean checkEmailExistence(String email) {
        User u = (User) session
                .createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();

        if (u != null) {
            return true;
        } else {
            return false;
        }
    }

//19
    public int selectPendingCartIdFromCart(String userName) {

        int cartId = -1;
        Criteria myCriteria = session.createCriteria(Cart.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("pending", 1));
        Cart cart = (Cart) myCriteria.uniqueResult();
        if (cart != null) {
            cartId = cart.getCartId();
        }
        return cartId;
    }

    //20
    public boolean insertBookIntoCart(int bookId, int bookQuantity, int cartId) {

        CartBook c = new CartBook();
        Book book = selectBookById(bookId);
        Cart cart = selectCartById(cartId);
        c.setId(new CartBookId(cartId, bookId));
        c.setBookQuantity(bookQuantity);
        c.setBook(book);
        c.setCart(cart);
        session.save(c);
        session.getTransaction().commit();
        return true;
    }
//21

    public Vector<Book> selectBooksFromCart(int cartId) {
        Vector<Book> books = new Vector<>();
        Criteria myCriteria = session.createCriteria(CartBook.class).add(Restrictions.eq("cart.id", cartId));
        List<CartBook> result = myCriteria.list();
        for (CartBook cartBook : result) {
            books.add(cartBook.getBook());
        }
        return books;
    }
//22

    public HashMap<Book, Integer> selectBooksWithQuantitiesFromCart(int cartId) {
        HashMap<Book, Integer> booksWithQuantity = new HashMap<>();

        Criteria myCriteria = session.createCriteria(CartBook.class).add(Restrictions.eq("cart.id", cartId));
        List<CartBook> cartBooks = myCriteria.list();
        for (CartBook cartBook : cartBooks) {
            booksWithQuantity.put(cartBook.getBook(), cartBook.getBookQuantity());
        }
        return booksWithQuantity;
    }
//23

    public boolean editUserData(User user) {
        session.update(user);
        if (!session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        return true;
//        if (user.getProfilePicUrl() != null) {
//            try {
//                Statement statement1 = connection.createStatement();
//                String queryString1 = "update user set password='" + user.getPassword() + "',credit_Limit=" + user.getCreditLimit() + ",job='" + user.getJob() + "',address='" + user.getAddress() + "',photo='" + user.getProfilePicUrl() + "' where email='" + user.getEmail() + "'";
//                statement1.executeUpdate(queryString1);
//                return true;
//            } catch (SQLException ex) {
//                Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
//                return false;
//            }
//        } else {
//            try {
//                Statement statement1 = connection.createStatement();
//                String queryString1 = "update user set password='" + user.getPassword() + "',credit_Limit=" + user.getCreditLimit() + ",job='" + user.getJob() + "',address='" + user.getAddress() + "'where email='" + user.getEmail() + "'";
//                statement1.executeUpdate(queryString1);
//                return true;
//            } catch (SQLException ex) {
//                Logger.getLogger(ManipulateDB.class.getName()).log(Level.SEVERE, null, ex);
//                return false;
//            }
//        }
    }
//24

    public boolean deleteBook(String userName, int bookId) {
        int cartId = selectPendingCartIdFromCart(userName);
        Criteria myCriteria = session.createCriteria(CartBook.class).add(Restrictions.eq("book.id", bookId)).add(Restrictions.eq("cart.id", cartId));
        CartBook cartBook = (CartBook) myCriteria.uniqueResult();
        session.delete(cartBook);
        if (!session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        return true;

    }
//25

    public Vector<Book> selectAllBooksInCategory(String categoryName) {
        Criteria criteria = session.createCriteria(Book.class).createCriteria("category").add(Restrictions.eq("categoryName", categoryName));
        List result = criteria.list();
        Vector<Book> allBooks = new Vector<>(result);

        return allBooks;
    }
//26

    public void updateCart(Cart cart) {
        Cart cart1 = selectCartById(cart.getCartId());
        cart1.setPending(cart.getPending());
        cart1.setTotal(cart.getTotal());
        session.update(cart1);
        session.getTransaction().commit();
    }

//27
    public void updateBook(Book book) {
        session.update(book);
        session.getTransaction().commit();
    }

//28
    public void updateAllBookInfo(Book book) {
        Criteria criteria = session.createCriteria(Book.class).add(Restrictions.eq("id", book.getBookId()));
        Book oldBook = (Book) criteria.uniqueResult();
        oldBook.setQuantity(book.getQuantity());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPrice(book.getPrice());
        oldBook.setDescription(book.getDescription());
        session.update(oldBook);
        session.getTransaction().commit();
    }
//29

    public boolean selectBookIdFromCart(int cartId, int bookId) {

        CartBook cb = (CartBook) session.createCriteria(CartBook.class)
                .add(Restrictions.eq("cart", (Cart) session.get(Cart.class, cartId)))
                .add(Restrictions.eq("book", (Book) session.get(Book.class, bookId))).uniqueResult();
        if (cb != null) {
            return true;
        } else {
            return false;
        }
    }

//30
    public boolean increaseBookQuantityInCartByOne(int cartId, int bookId) {

        Criteria myCriteria = session.createCriteria(CartBook.class).add(Restrictions.eq("book.id", bookId)).add(Restrictions.eq("cart.id", cartId));
        CartBook cartBook = (CartBook) myCriteria.uniqueResult();
        cartBook.setBookQuantity(cartBook.getBookQuantity() + 1);
        session.update(cartBook);
        if (!session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        return true;
    }
//31

    public boolean updateBookCountInCart(int cartID, int bookId, int value) {
        try {
            Criteria cartBookCriteria = session.createCriteria(CartBook.class, "cb").createAlias("cart", "c").add(Restrictions.and(Restrictions.eq("c.cartId", cartID), Restrictions.eq("cb.book.bookId", bookId)));
            CartBook cartBook = (CartBook) cartBookCriteria.uniqueResult();
            cartBook.setBookQuantity(value);                     //quantity type must be edited
            session.update(cartBook);
            if (!session.getTransaction().wasCommitted()) {
                session.getTransaction().commit();
            }
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }
//32

    public Vector<Cart> selectAllPastCarts(String userName) {

        Criteria myCriteria = session.createCriteria(Cart.class).add(Restrictions.eq("userName", userName)).add(Restrictions.eq("pending", 0));
        List<Cart> carts = myCriteria.list();
        Vector<Cart> pastCarts = new Vector<>(carts);
        return pastCarts;
    }
}
