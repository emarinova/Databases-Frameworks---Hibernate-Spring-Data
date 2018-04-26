package app;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p03 {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();

        List<Employee> employeesList = em.createQuery("FROM Employee ").getResultList();
        List<String> fullName = new ArrayList<>();

        for (Employee e : employeesList) {
            fullName.add(e.getFirstName()+" "+e.getLastName());
        }

        if (fullName.contains(name)){
            System.out.println("Yes");
        } else
            System.out.println("No");

        em.close();
        factory.close();
    }
}
