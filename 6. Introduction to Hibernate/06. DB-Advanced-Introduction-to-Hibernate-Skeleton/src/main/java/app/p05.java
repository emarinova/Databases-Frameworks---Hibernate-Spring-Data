package app;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class p05 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        List<Employee> employeeList = em.createQuery("FROM Employee WHERE department.id = 6 ORDER BY salary, id").getResultList();

        for (Employee e : employeeList){
            System.out.printf("%s %s from %s - %.2f%n", e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary());
        }
        em.close();
        factory.close();
    }
}
