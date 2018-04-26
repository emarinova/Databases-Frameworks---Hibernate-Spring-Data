package app;

        import entities.Address;
        import entities.Employee;
        import entities.Town;

        import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import javax.persistence.Persistence;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.List;

public class p11 {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        int counter = 0;
        try {

            Town town = (Town) em.createQuery("FROM Town WHERE name =:parameter").setParameter("parameter", input).getSingleResult();
            List<Address> addresses = em.createQuery("FROM Address WHERE town.id=:parameter1").setParameter("parameter1", town.getId()).getResultList();

            em.getTransaction().begin();

            for (Address a : addresses) {

                for (Employee e : a.getEmployees()) {
                    if (addresses.contains(e.getAddress())){
                        e.setAddress(null);
                        em.persist(e);
                    }
                }
                em.remove(a);
                counter++;
            }

            em.remove(town);
            em.getTransaction().commit();
            System.out.printf("%d addresses in %s was deleted", counter, input);

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            em.close();
            factory.close();
        }
    }
}
