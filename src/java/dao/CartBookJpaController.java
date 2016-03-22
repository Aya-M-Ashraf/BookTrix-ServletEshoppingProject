/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Book;
import Entities.Cart;
import Entities.CartBook;
import Entities.CartBookPK;
import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ahmed
 */
public class CartBookJpaController implements Serializable {

    public CartBookJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CartBook cartBook) throws PreexistingEntityException, Exception {
        if (cartBook.getCartBookPK() == null) {
            cartBook.setCartBookPK(new CartBookPK());
        }
        cartBook.getCartBookPK().setCartId(cartBook.getCart().getCartId());
        cartBook.getCartBookPK().setBookId(cartBook.getBook().getBookId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book book = cartBook.getBook();
            if (book != null) {
                book = em.getReference(book.getClass(), book.getBookId());
                cartBook.setBook(book);
            }
            Cart cart = cartBook.getCart();
            if (cart != null) {
                cart = em.getReference(cart.getClass(), cart.getCartId());
                cartBook.setCart(cart);
            }
            em.persist(cartBook);
            if (book != null) {
                book.getCartBookCollection().add(cartBook);
                book = em.merge(book);
            }
            if (cart != null) {
                cart.getCartBookCollection().add(cartBook);
                cart = em.merge(cart);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCartBook(cartBook.getCartBookPK()) != null) {
                throw new PreexistingEntityException("CartBook " + cartBook + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CartBook cartBook) throws NonexistentEntityException, Exception {
        cartBook.getCartBookPK().setCartId(cartBook.getCart().getCartId());
        cartBook.getCartBookPK().setBookId(cartBook.getBook().getBookId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CartBook persistentCartBook = em.find(CartBook.class, cartBook.getCartBookPK());
            Book bookOld = persistentCartBook.getBook();
            Book bookNew = cartBook.getBook();
            Cart cartOld = persistentCartBook.getCart();
            Cart cartNew = cartBook.getCart();
            if (bookNew != null) {
                bookNew = em.getReference(bookNew.getClass(), bookNew.getBookId());
                cartBook.setBook(bookNew);
            }
            if (cartNew != null) {
                cartNew = em.getReference(cartNew.getClass(), cartNew.getCartId());
                cartBook.setCart(cartNew);
            }
            cartBook = em.merge(cartBook);
            if (bookOld != null && !bookOld.equals(bookNew)) {
                bookOld.getCartBookCollection().remove(cartBook);
                bookOld = em.merge(bookOld);
            }
            if (bookNew != null && !bookNew.equals(bookOld)) {
                bookNew.getCartBookCollection().add(cartBook);
                bookNew = em.merge(bookNew);
            }
            if (cartOld != null && !cartOld.equals(cartNew)) {
                cartOld.getCartBookCollection().remove(cartBook);
                cartOld = em.merge(cartOld);
            }
            if (cartNew != null && !cartNew.equals(cartOld)) {
                cartNew.getCartBookCollection().add(cartBook);
                cartNew = em.merge(cartNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CartBookPK id = cartBook.getCartBookPK();
                if (findCartBook(id) == null) {
                    throw new NonexistentEntityException("The cartBook with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CartBookPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CartBook cartBook;
            try {
                cartBook = em.getReference(CartBook.class, id);
                cartBook.getCartBookPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cartBook with id " + id + " no longer exists.", enfe);
            }
            Book book = cartBook.getBook();
            if (book != null) {
                book.getCartBookCollection().remove(cartBook);
                book = em.merge(book);
            }
            Cart cart = cartBook.getCart();
            if (cart != null) {
                cart.getCartBookCollection().remove(cartBook);
                cart = em.merge(cart);
            }
            em.remove(cartBook);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CartBook> findCartBookEntities() {
        return findCartBookEntities(true, -1, -1);
    }

    public List<CartBook> findCartBookEntities(int maxResults, int firstResult) {
        return findCartBookEntities(false, maxResults, firstResult);
    }

    private List<CartBook> findCartBookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CartBook.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CartBook findCartBook(CartBookPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CartBook.class, id);
        } finally {
            em.close();
        }
    }

    public int getCartBookCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CartBook> rt = cq.from(CartBook.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
