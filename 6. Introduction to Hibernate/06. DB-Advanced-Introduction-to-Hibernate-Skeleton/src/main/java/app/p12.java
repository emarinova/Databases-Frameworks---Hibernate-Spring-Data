package app;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class p12 {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String pattern = reader.readLine().toLowerCase();

        List<Employee> employeeList = em.createQuery("FROM Employee ").getResultList();

        for(Employee e : employeeList){
            if (e.getFirstName().toLowerCase().indexOf(pattern) == 0) {
                System.out.printf("%s %s - %s - ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary());
            }
        }

        em.close();
        factory.close();
    }
}
