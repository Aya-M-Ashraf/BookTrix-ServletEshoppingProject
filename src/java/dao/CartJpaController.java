/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entities.Cart;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class CartJpaController implements Serializable {

    public CartJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cart cart) {
        if (cart.getCartBookCollection() == null) {
            cart.setCartBookCollection(new ArrayList<CartBook>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CartBook> attachedCartBookCollection = new ArrayList<CartBook>();
            for (CartBook cartBookCollectionCartBookToAttach : cart.getCartBookCollection()) {
                cartBookCollectionCartBookToAttach = em.getReference(cartBookCollectionCartBookToAttach.getClass(), cartBookCollectionCartBookToAttach.getCartBookPK());
                attachedCartBookCollection.add(cartBookCollectionCartBookToAttach);
            }
            cart.setCartBookCollection(attachedCartBookCollection);
            em.persist(cart);
            for (CartBook cartBookCollectionCartBook : cart.getCartBookCollection()) {
                Cart oldCartOfCartBookCollectionCartBook = cartBookCollectionCartBook.getCart();
                cartBookCollectionCartBook.setCart(cart);
                cartBookCollectionCartBook = em.merge(cartBookCollectionCartBook);
                if (oldCartOfCartBookCollectionCartBook != null) {
                    oldCartOfCartBookCollectionCartBook.getCartBookCollection().remove(cartBookCollectionCartBook);
                    oldCartOfCartBookCollectionCartBook = em.merge(oldCartOfCartBookCollectionCartBook);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cart cart) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cart persistentCart = em.find(Cart.class, cart.getCartId());
            Collection<CartBook> cartBookCollectionOld = persistentCart.getCartBookCollection();
            Collection<CartBook> cartBookCollectionNew = cart.getCartBookCollection();
            List<String> illegalOrphanMessages = null;
            for (CartBook cartBookCollectionOldCartBook : cartBookCollectionOld) {
                if (!cartBookCollectionNew.contains(cartBookCollectionOldCartBook)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CartBook " + cartBookCollectionOldCartBook + " since its cart field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CartBook> attachedCartBookCollectionNew = new ArrayList<CartBook>();
            for (CartBook cartBookCollectionNewCartBookToAttach : cartBookCollectionNew) {
                cartBookCollectionNewCartBookToAttach = em.getReference(cartBookCollectionNewCartBookToAttach.getClass(), cartBookCollectionNewCartBookToAttach.getCartBookPK());
                attachedCartBookCollectionNew.add(cartBookCollectionNewCartBookToAttach);
            }
            cartBookCollectionNew = attachedCartBookCollectionNew;
            cart.setCartBookCollection(cartBookCollectionNew);
            cart = em.merge(cart);
            for (CartBook cartBookCollectionNewCartBook : cartBookCollectionNew) {
                if (!cartBookCollectionOld.contains(cartBookCollectionNewCartBook)) {
                    Cart oldCartOfCartBookCollectionNewCartBook = cartBookCollectionNewCartBook.getCart();
                    cartBookCollectionNewCartBook.setCart(cart);
                    cartBookCollectionNewCartBook = em.merge(cartBookCollectionNewCartBook);
                    if (oldCartOfCartBookCollectionNewCartBook != null && !oldCartOfCartBookCollectionNewCartBook.equals(cart)) {
                        oldCartOfCartBookCollectionNewCartBook.getCartBookCollection().remove(cartBookCollectionNewCartBook);
                        oldCartOfCartBookCollectionNewCartBook = em.merge(oldCartOfCartBookCollectionNewCartBook);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cart.getCartId();
                if (findCart(id) == null) {
                    throw new NonexistentEntityException("The cart with id " + id + " no longer exists.");
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
            Cart cart;
            try {
                cart = em.getReference(Cart.class, id);
                cart.getCartId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cart with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CartBook> cartBookCollectionOrphanCheck = cart.getCartBookCollection();
            for (CartBook cartBookCollectionOrphanCheckCartBook : cartBookCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cart (" + cart + ") cannot be destroyed since the CartBook " + cartBookCollectionOrphanCheckCartBook + " in its cartBookCollection field has a non-nullable cart field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cart);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cart> findCartEntities() {
        return findCartEntities(true, -1, -1);
    }

    public List<Cart> findCartEntities(int maxResults, int firstResult) {
        return findCartEntities(false, maxResults, firstResult);
    }

    private List<Cart> findCartEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cart.class));
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

    public Cart findCart(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cart.class, id);
        } finally {
            em.close();
        }
    }

    public int getCartCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cart> rt = cq.from(Cart.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
