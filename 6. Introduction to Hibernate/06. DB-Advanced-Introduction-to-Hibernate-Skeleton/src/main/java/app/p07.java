package app;

import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class p07 {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        List<Address> addresses = em.createQuery("FROM Address ").getResultList();

        Comparator<Address> compByEmployees = Comparator.comparingInt(a -> a.getEmployees().size());
        Comparator<Address> compByTownId = Comparator.comparingInt(a -> a.getTown().getId());

        addresses.stream().sorted(compByEmployees.reversed().thenComparing(compByTownId)).forEach(a ->
                System.out.printf("%s, %s - %d employees%n", a.getText(), a.getTown().getName(), a.getEmployees().size()));
        em.close();
        factory.close();
    }
}