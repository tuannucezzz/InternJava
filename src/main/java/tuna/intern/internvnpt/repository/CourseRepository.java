package tuna.intern.internvnpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuna.intern.internvnpt.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
