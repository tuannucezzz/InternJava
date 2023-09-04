package tuna.intern.internvnpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuna.intern.internvnpt.domain.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select s.id, s.name, s.phone, c.name as class_name from students s inner join classes c on s.id=c.student_id", nativeQuery = true)
    List<Object[]> findStudent();

    @Query(value = "select * from students order by id asc ", nativeQuery = true)
    List<Student> listStudent();

    List<Student> findAllById(Integer id);
}
