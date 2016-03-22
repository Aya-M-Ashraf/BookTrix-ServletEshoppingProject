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
import Entities.Category;
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
public class CategoryJpaController implements Serializable {

    public CategoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Category category) {
        if (category.getBookCollection() == null) {
            category.setBookCollection(new ArrayList<Book>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Book> attachedBookCollection = new ArrayList<Book>();
            for (Book bookCollectionBookToAttach : category.getBookCollection()) {
                bookCollectionBookToAttach = em.getReference(bookCollectionBookToAttach.getClass(), bookCollectionBookToAttach.getBookId());
                attachedBookCollection.add(bookCollectionBookToAttach);
            }
            category.setBookCollection(attachedBookCollection);
            em.persist(category);
            for (Book bookCollectionBook : category.getBookCollection()) {
                Category oldCategoryIdOfBookCollectionBook = bookCollectionBook.getCategoryId();
                bookCollectionBook.setCategoryId(category);
                bookCollectionBook = em.merge(bookCollectionBook);
                if (oldCategoryIdOfBookCollectionBook != null) {
                    oldCategoryIdOfBookCollectionBook.getBookCollection().remove(bookCollectionBook);
                    oldCategoryIdOfBookCollectionBook = em.merge(oldCategoryIdOfBookCollectionBook);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Category category) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category persistentCategory = em.find(Category.class, category.getCategoryId());
            Collection<Book> bookCollectionOld = persistentCategory.getBookCollection();
            Collection<Book> bookCollectionNew = category.getBookCollection();
            Collection<Book> attachedBookCollectionNew = new ArrayList<Book>();
            for (Book bookCollectionNewBookToAttach : bookCollectionNew) {
                bookCollectionNewBookToAttach = em.getReference(bookCollectionNewBookToAttach.getClass(), bookCollectionNewBookToAttach.getBookId());
                attachedBookCollectionNew.add(bookCollectionNewBookToAttach);
            }
            bookCollectionNew = attachedBookCollectionNew;
            category.setBookCollection(bookCollectionNew);
            category = em.merge(category);
            for (Book bookCollectionOldBook : bookCollectionOld) {
                if (!bookCollectionNew.contains(bookCollectionOldBook)) {
                    bookCollectionOldBook.setCategoryId(null);
                    bookCollectionOldBook = em.merge(bookCollectionOldBook);
                }
            }
            for (Book bookCollectionNewBook : bookCollectionNew) {
                if (!bookCollectionOld.contains(bookCollectionNewBook)) {
                    Category oldCategoryIdOfBookCollectionNewBook = bookCollectionNewBook.getCategoryId();
                    bookCollectionNewBook.setCategoryId(category);
                    bookCollectionNewBook = em.merge(bookCollectionNewBook);
                    if (oldCategoryIdOfBookCollectionNewBook != null && !oldCategoryIdOfBookCollectionNewBook.equals(category)) {
                        oldCategoryIdOfBookCollectionNewBook.getBookCollection().remove(bookCollectionNewBook);
                        oldCategoryIdOfBookCollectionNewBook = em.merge(oldCategoryIdOfBookCollectionNewBook);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = category.getCategoryId();
                if (findCategory(id) == null) {
                    throw new NonexistentEntityException("The category with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category category;
            try {
                category = em.getReference(Category.class, id);
                category.getCategoryId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The category with id " + id + " no longer exists.", enfe);
            }
            Collection<Book> bookCollection = category.getBookCollection();
            for (Book bookCollectionBook : bookCollection) {
                bookCollectionBook.setCategoryId(null);
                bookCollectionBook = em.merge(bookCollectionBook);
            }
            em.remove(category);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Category> findCategoryEntities() {
        return findCategoryEntities(true, -1, -1);
    }

    public List<Category> findCategoryEntities(int maxResults, int firstResult) {
        return findCategoryEntities(false, maxResults, firstResult);
    }

    private List<Category> findCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Category.class));
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

    public Category findCategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Category> rt = cq.from(Category.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
