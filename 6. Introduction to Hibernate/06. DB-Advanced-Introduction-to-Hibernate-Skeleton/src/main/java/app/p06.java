package app;

import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class p06 {
    public static void main(String[] args) throws IOException {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lastN = reader.readLine();

        List<Employee> employeeList = em.createQuery("FROM Employee WHERE lastName=:parameter")
                .setParameter("parameter", lastN).getResultList();

        Address address = new Address();
        address.setText("Vitoshka 15");

        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();

        em.getTransaction().begin();
        employeeList.get(0).setAddress(address);
        em.getTransaction().commit();

        em.close();
        factory.close();
    }
}
