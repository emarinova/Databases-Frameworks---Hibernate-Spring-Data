package app;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class p09 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        List<Project> projects = em.createQuery("FROM Project ").getResultList();

        Comparator<Project> comp = Comparator.comparing(p -> p.getStartDate());
        projects.stream().sorted(comp.reversed()).limit(10)
                .sorted(Comparator.comparing(p -> p.getName())).forEach(p ->
                System.out.printf("Project name: %s%n   Project Description: %s%n   Project Start Date: %s%n" +
                        "   Project End Date: %s%n", p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate()));

        em.close();
        factory.close();
    }
}
