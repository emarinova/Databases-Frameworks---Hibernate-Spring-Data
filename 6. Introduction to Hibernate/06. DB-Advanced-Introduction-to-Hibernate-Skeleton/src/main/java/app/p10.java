package app;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class p10 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        List<Employee> employees = em.createQuery("FROM Employee ").getResultList();
        for (Employee e : employees){
            if(e.getDepartment().getName().equals("Engineering") || e.getDepartment().getName().equals("Tool Design") ||
                    e.getDepartment().getName().equals("Marketing") || e.getDepartment().getName().equals("Information Services")){
                e.setSalary(e.getSalary().multiply( BigDecimal.valueOf(1.12)));
                System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
            }
        }
        em.close();
        factory.close();
    }
}
