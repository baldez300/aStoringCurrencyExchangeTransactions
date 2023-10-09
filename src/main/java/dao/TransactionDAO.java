package dao;

import datasource.MariaDbConnectionJPA;
import entity.Transaction;
import jakarta.persistence.EntityManager;

public class TransactionDAO {

    public void persist(Transaction transaction) {
        EntityManager em = MariaDbConnectionJPA.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(transaction);
            em.getTransaction().commit();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    // Here other methods for fetching, updating, or deleting transactions as needed.
}
