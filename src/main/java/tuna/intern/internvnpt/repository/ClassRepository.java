package tuna.intern.internvnpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tuna.intern.internvnpt.domain.Classes;
import tuna.intern.internvnpt.dto.ClassCourseDto;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Integer>{
    @Query(value = "select c.id, c.name, co.course_name from classes c inner join courses co on c.course_id=co.id", nativeQuery = true)
    List<Object[]> findClass();

    @Query(value = "select * from classes c join courses co on c.course_id = co.id where co.id = :id", nativeQuery = true)
    List<Object[]> findClassByCourseId(int id);

    @Query(value = "select sc.class_id, c.name" + " from student_class sc join classes c on sc.class_id = c.id "+"where student_id = :param", nativeQuery = true)
    List<Object[]> listClassOfStudent(@Param("param") Integer id);

    @Query(value = "SELECT c.id, c.name, co.course_name " +
                    "FROM classes c JOIN courses co ON c.course_id = co.id " +
                    "WHERE c.id NOT IN ( " +
                    "    SELECT class_id " +
                    "    FROM student_class " +
                    "    WHERE student_id = :param) ", nativeQuery = true)
    List<Object[]> listClassNotRegist(@Param("param") Integer id);

    @Modifying
    @Query(value = "DELETE FROM student_class " + "WHERE student_id = :studentId", nativeQuery = true)
    void unregistClass(@Param("studentId") int studentId);

    @Query(value = "SELECT new tuna.intern.internvnpt.dto.ClassCourseDto" +
            "(c.id,c.name)" +
            " FROM Classes c JOIN Course co on c.course.id = co.id")
    List<ClassCourseDto>findClassCourse();
}
