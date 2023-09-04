package tuna.intern.internvnpt;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.QueryHints;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tuna.intern.internvnpt.domain.Student;
import tuna.intern.internvnpt.domain.UserEntity;
import tuna.intern.internvnpt.dto.StudentDto;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
@Slf4j
class InternVnptApplicationTests {
    @Autowired
    public EntityManagerFactory emf;

    @Test
    void contextLoads() {
    }

    @Test
    public void testWithoutJoinFetch() {
        log.info("select from without join fetch");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Student student = em.createQuery("SELECT s from Student s where id = 13", Student.class).getSingleResult();

        log.info("commit transaction and close Session");
        em.getTransaction().commit();
        em.close();

        try {
            log.info(student.getName() + " join " + student.getAssignClass().size() + " class");
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw e;
        }
    }

    @Test
    public void testWithJoinFetch() {
        log.info("select from without join fetch");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Student> q = em.createQuery("SELECT distinct s from Student s left join fetch s.assignClass", Student.class);
        q.setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
        List<Student> students = q.getResultList();
        for (Student s : students) {
            log.info(s.getName() + " join " + s.getAssignClass().size() + " class");
        }

        log.info("commit transaction and close Session");
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testHibernate() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Session session1 = factory.openSession();

        UserEntity user1 = session1.get(UserEntity.class, 3);
        System.out.println(user1);
        session1.close();

        Session session2 = factory.openSession();

        UserEntity user2 = session1.get(UserEntity.class, 3);
        System.out.println(user2);
        session2.close();
    }
}
