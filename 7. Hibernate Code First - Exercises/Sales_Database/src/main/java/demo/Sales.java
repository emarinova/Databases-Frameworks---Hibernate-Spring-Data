package demo;

import model.Customer;
import model.Product;
import model.Sale;
import model.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;

public class Sales {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("sales_database");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Sale sale1 = new Sale();

        Product product1 = new Product("product1", 2.00, BigDecimal.valueOf(3.45));
        em.persist(product1);
        Customer customer1 = new Customer("name", "name@email.com", "hfshjfhjs38fgh222");
        em.persist(customer1);
        StoreLocation store = new StoreLocation("Vitoshka 12");
        em.persist(store);

        sale1.setCustomer(customer1);
        sale1.setProduct(product1);
        sale1.setStoreLocation(store);

        em.persist(sale1);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
