package demo;

import model.Gringott;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;

public class GringottsDemo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gringotts");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Gringott wizard = new Gringott();
        wizard.setLastName("akdahf");
        wizard.setAge(34);

        em.persist(wizard);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}