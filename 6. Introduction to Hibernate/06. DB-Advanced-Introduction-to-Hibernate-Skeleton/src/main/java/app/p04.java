package app;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class p04 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        List<Employee> employeeList = em.createQuery("FROM Employee WHERE salary > 50000 ").getResultList();

        for (Employee e : employeeList){
            System.out.println(e.getFirstName());
        }
        em.close();
        factory.close();
    }
}
