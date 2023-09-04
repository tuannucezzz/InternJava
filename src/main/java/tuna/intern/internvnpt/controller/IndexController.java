package tuna.intern.internvnpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tuna.intern.internvnpt.domain.Classes;
import tuna.intern.internvnpt.domain.Course;
import tuna.intern.internvnpt.domain.Student;
import tuna.intern.internvnpt.dto.ClassCourseDto;
import tuna.intern.internvnpt.dto.ClassDto;
import tuna.intern.internvnpt.dto.CourseDto;
import tuna.intern.internvnpt.dto.StudentDto;
import tuna.intern.internvnpt.repository.ClassRepository;
import tuna.intern.internvnpt.repository.StudentRepository;
import tuna.intern.internvnpt.service.ClassService;
import tuna.intern.internvnpt.service.CourseService;
import tuna.intern.internvnpt.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassRepository classRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String viewCoursePage(Model model) {
        List<CourseDto> courseList = courseService.listAll();
        model.addAttribute("listcourse", courseList);
        return  "course";
    }

    @RequestMapping(value = "/class", method = RequestMethod.GET)
    public String viewClassPage(Model model) {
        List<ClassCourseDto> list = new ArrayList<>();
        for(Object[] o : classRepository.findClass()) {
            ClassCourseDto clco = new ClassCourseDto();
            clco.setId(Integer.parseInt(String.valueOf(o[0])));
            clco.setClassName(String.valueOf(o[1]));
            clco.setCourseName(String.valueOf(o[2]));
            list.add(clco);
        }
        model.addAttribute("listclass", list);
        return "class";
    }

    @RequestMapping(value = "/class-test", method = RequestMethod.GET)
    public String viewClassPageTest(Model model) {
        List<ClassCourseDto> list = new ArrayList<>();
        for(ClassCourseDto o : classRepository.findClassCourse()) {
            ClassCourseDto clco = new ClassCourseDto();
            clco.setId(o.getId());
            clco.setClassName(String.valueOf(o.getClassName()));
            list.add(clco);
        }
        model.addAttribute("listclass", list);
        return "class";
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String viewStudentListPage(Model model) {
        List<StudentDto> stulist = studentService.listAll();
        model.addAttribute("stulist", stulist);
        return "student";
    }

    @RequestMapping(value = "student-list")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.listAllTest();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/student/test/{id}")
    @Cacheable(cacheNames = "cacheStore", key = "#id")
    public Student getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return student;
    }
}