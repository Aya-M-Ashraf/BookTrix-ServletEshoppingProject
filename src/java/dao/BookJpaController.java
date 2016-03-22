/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entities.Book;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Category;
import Entities.CartBook;
import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ahmed
 */
public class BookJpaController implements Serializable {

    public BookJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Book book) {
        if (book.getCartBookCollection() == null) {
            book.setCartBookCollection(new ArrayList<CartBook>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category categoryId = book.getCategoryId();
            if (categoryId != null) {
                categoryId = em.getReference(categoryId.getClass(), categoryId.getCategoryId());
                book.setCategoryId(categoryId);
            }
            Collection<CartBook> attachedCartBookCollection = new ArrayList<CartBook>();
            for (CartBook cartBookCollectionCartBookToAttach : book.getCartBookCollection()) {
                cartBookCollectionCartBookToAttach = em.getReference(cartBookCollectionCartBookToAttach.getClass(), cartBookCollectionCartBookToAttach.getCartBookPK());
                attachedCartBookCollection.add(cartBookCollectionCartBookToAttach);
            }
            book.setCartBookCollection(attachedCartBookCollection);
            em.persist(book);
            if (categoryId != null) {
                categoryId.getBookCollection().add(book);
                categoryId = em.merge(categoryId);
            }
            for (CartBook cartBookCollectionCartBook : book.getCartBookCollection()) {
                Book oldBookOfCartBookCollectionCartBook = cartBookCollectionCartBook.getBook();
                cartBookCollectionCartBook.setBook(book);
                cartBookCollectionCartBook = em.merge(cartBookCollectionCartBook);
                if (oldBookOfCartBookCollectionCartBook != null) {
                    oldBookOfCartBookCollectionCartBook.getCartBookCollection().remove(cartBookCollectionCartBook);
                    oldBookOfCartBookCollectionCartBook = em.merge(oldBookOfCartBookCollectionCartBook);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Book book) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book persistentBook = em.find(Book.class, book.getBookId());
            Category categoryIdOld = persistentBook.getCategoryId();
            Category categoryIdNew = book.getCategoryId();
            Collection<CartBook> cartBookCollectionOld = persistentBook.getCartBookCollection();
            Collection<CartBook> cartBookCollectionNew = book.getCartBookCollection();
            List<String> illegalOrphanMessages = null;
            for (CartBook cartBookCollectionOldCartBook : cartBookCollectionOld) {
                if (!cartBookCollectionNew.contains(cartBookCollectionOldCartBook)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CartBook " + cartBookCollectionOldCartBook + " since its book field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoryIdNew != null) {
                categoryIdNew = em.getReference(categoryIdNew.getClass(), categoryIdNew.getCategoryId());
                book.setCategoryId(categoryIdNew);
            }
            Collection<CartBook> attachedCartBookCollectionNew = new ArrayList<CartBook>();
            for (CartBook cartBookCollectionNewCartBookToAttach : cartBookCollectionNew) {
                cartBookCollectionNewCartBookToAttach = em.getReference(cartBookCollectionNewCartBookToAttach.getClass(), cartBookCollectionNewCartBookToAttach.getCartBookPK());
                attachedCartBookCollectionNew.add(cartBookCollectionNewCartBookToAttach);
            }
            cartBookCollectionNew = attachedCartBookCollectionNew;
            book.setCartBookCollection(cartBookCollectionNew);
            book = em.merge(book);
            if (categoryIdOld != null && !categoryIdOld.equals(categoryIdNew)) {
                categoryIdOld.getBookCollection().remove(book);
                categoryIdOld = em.merge(categoryIdOld);
            }
            if (categoryIdNew != null && !categoryIdNew.equals(categoryIdOld)) {
                categoryIdNew.getBookCollection().add(book);
                categoryIdNew = em.merge(categoryIdNew);
            }
            for (CartBook cartBookCollectionNewCartBook : cartBookCollectionNew) {
                if (!cartBookCollectionOld.contains(cartBookCollectionNewCartBook)) {
                    Book oldBookOfCartBookCollectionNewCartBook = cartBookCollectionNewCartBook.getBook();
                    cartBookCollectionNewCartBook.setBook(book);
                    cartBookCollectionNewCartBook = em.merge(cartBookCollectionNewCartBook);
                    if (oldBookOfCartBookCollectionNewCartBook != null && !oldBookOfCartBookCollectionNewCartBook.equals(book)) {
                        oldBookOfCartBookCollectionNewCartBook.getCartBookCollection().remove(cartBookCollectionNewCartBook);
                        oldBookOfCartBookCollectionNewCartBook = em.merge(oldBookOfCartBookCollectionNewCartBook);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = book.getBookId();
                if (findBook(id) == null) {
                    throw new NonexistentEntityException("The book with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Book book;
            try {
                book = em.getReference(Book.class, id);
                book.getBookId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The book with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CartBook> cartBookCollectionOrphanCheck = book.getCartBookCollection();
            for (CartBook cartBookCollectionOrphanCheckCartBook : cartBookCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Book (" + book + ") cannot be destroyed since the CartBook " + cartBookCollectionOrphanCheckCartBook + " in its cartBookCollection field has a non-nullable book field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Category categoryId = book.getCategoryId();
            if (categoryId != null) {
                categoryId.getBookCollection().remove(book);
                categoryId = em.merge(categoryId);
            }
            em.remove(book);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Book> findBookEntities() {
        return findBookEntities(true, -1, -1);
    }

    public List<Book> findBookEntities(int maxResults, int firstResult) {
        return findBookEntities(false, maxResults, firstResult);
    }

    private List<Book> findBookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Book.class));
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

    public Book findBook(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Book> rt = cq.from(Book.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
