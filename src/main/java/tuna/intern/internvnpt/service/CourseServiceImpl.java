package tuna.intern.internvnpt.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tuna.intern.internvnpt.domain.Course;
import tuna.intern.internvnpt.dto.CourseDto;
import tuna.intern.internvnpt.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CourseDto> listAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map((course) -> modelToDto(course)).collect(Collectors.toList());
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course createCourse(CourseDto courseDto) {
        Course course = dtoToModel(courseDto);
        Course newCourse = courseRepository.save(course);
        return newCourse;
    }

    @Override
    public Course get(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        courseRepository.deleteById(id);
    }

    private Course dtoToModel(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto, Course.class);
        return course;
    }

    private CourseDto modelToDto(Course course) {
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        return courseDto;
    }
}
