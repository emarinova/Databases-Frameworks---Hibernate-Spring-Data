package demo;

import model.AmmoniumChloride;
import model.BasicIngredient;
import model.Mint;
import model.Nettle;
import product.BasicShampoo;
import product.ClassicLabel;
import product.SpringShampoo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ShampooCompanyDemo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shampoo_company");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        //BasicIngredient ingredient1 = new Mint();
        //BasicIngredient ingredient2 = new Nettle();
        //BasicIngredient ingredient3 = new AmmoniumChloride();
//
        //em.persist(ingredient1);
        //em.persist(ingredient2);
        //em.persist(ingredient3);
//
        //List<BasicIngredient> ingredientSet = em.createQuery("FROM BasicIngredient ").getResultList();
        //for(BasicIngredient ingredient : ingredientSet){
        //    System.out.println(ingredient.toString());
        //}

        ClassicLabel label = new ClassicLabel("Spring shampoo", "Nettle and mint");
        em.persist(label);

        BasicShampoo springShampoo = new SpringShampoo();
        springShampoo.setLabel(label);
        em.persist(springShampoo);

        em.getTransaction().commit();
        em.close();
    }
}
