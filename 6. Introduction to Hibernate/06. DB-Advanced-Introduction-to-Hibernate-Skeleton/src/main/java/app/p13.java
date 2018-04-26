package app;

import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class p13 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        Comparator<Employee> comp = Comparator.comparing(e -> e.getSalary());

        List<Department> departments = em.createQuery("FROM Department ").getResultList();
        for (Department d : departments){
            d.getEmployees().stream().sorted(comp.reversed()).limit(1).forEach(employee -> {
                if (employee.getSalary().compareTo(BigDecimal.valueOf(30000))==-1 || employee.getSalary().compareTo(BigDecimal.valueOf(70000))==1){
                    System.out.printf("%s - %.2f%n", d.getName(), employee.getSalary());
                }
            });
        }
        em.close();
        factory.close();
    }
}
