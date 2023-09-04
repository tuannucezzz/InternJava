package tuna.intern.internvnpt.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuna.intern.internvnpt.domain.Classes;
import tuna.intern.internvnpt.domain.Course;
import tuna.intern.internvnpt.domain.Student;
import tuna.intern.internvnpt.dto.CourseDto;
import tuna.intern.internvnpt.dto.StudentDto;
import tuna.intern.internvnpt.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface CourseService {
    public List<CourseDto> listAll();

    public void save(Course course);

    public Course createCourse(CourseDto courseDto);

    public Course get(int id);

    public void delete(int id);
}
