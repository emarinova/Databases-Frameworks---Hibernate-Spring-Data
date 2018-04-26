package demo;

import model.Course;
import model.Person;
import model.Student;
import model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;

public class University {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("university");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Student student1 = new Student("Ivan", "Ivanov", "2952952", 4.5, "Attendance");
        em.persist(student1);
        Teacher teacher1 = new Teacher("Petur", "Petrov", "35353", "petrov@gmail.com", 16.7);
        em.persist(teacher1);
        Course course1 = new Course();
        course1.setName("COURSE!!!!");
        course1.setTeacher(teacher1);
        course1.setStudents(new HashSet<>());
        course1.getStudents().add(student1);
        em.persist(course1);

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
