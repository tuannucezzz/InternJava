package tuna.intern.internvnpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tuna.intern.internvnpt.domain.Classes;
import tuna.intern.internvnpt.domain.Student;
import tuna.intern.internvnpt.dto.StudentClassDto;
import tuna.intern.internvnpt.dto.StudentDto;
import tuna.intern.internvnpt.repository.ClassRepository;
import tuna.intern.internvnpt.repository.StudentRepository;
import tuna.intern.internvnpt.service.ClassService;
import tuna.intern.internvnpt.service.StudentService;

import java.util.*;

@Controller
@RequestMapping("/student")
@Validated
@CacheConfig(cacheNames = "student")
public class StudentController {
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ClassRepository classRepository;

    public StudentController(StudentRepository studentRepository, ClassRepository classRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
    }

    @GetMapping("/addstudent")
    public String addStudent(Model model) {
        List<StudentDto> liststudent = studentService.listAll();
        model.addAttribute("liststudent", liststudent);
        model.addAttribute("student", new Student());
        return "addstudent";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") StudentDto student, RedirectAttributes ra) {
        studentService.createStudent(student);
        ra.addFlashAttribute("success_message", "The student has been saved successfully.");
        return "redirect:/student";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("addstudent");
        Student stu = studentService.get(id);
        mav.addObject("student", stu);
        return mav;
    }

    @RequestMapping("/delete/{id}")
//    @CacheEvict(key = "#id")
    public String deleteStudentPage(@PathVariable(name = "id") int id, RedirectAttributes ra) {
        studentService.delete(id);
        ra.addFlashAttribute("error_message", "The student has been deleted successfully.");
        return "redirect:/student";
    }

    @RequestMapping("/{stuId}/class")
    public ModelAndView showRegisterClassForm(@PathVariable(name = "stuId") int id) {
        ModelAndView mav = new ModelAndView("classregistration");
        List<Classes> list = classService.listAll();
        Student stu = studentService.get(id);
        mav.addObject("listclass", list);
        mav.addObject("student", stu);
        return mav;
    }

//    @RequestMapping("/{stuId}/class")
//    public ModelAndView listClassNotRegist(@PathVariable(name = "stuId") int id) {
//        ModelAndView mav = new ModelAndView("classregistration");
//        List<ClassCourseDto> list = new ArrayList<>();
//        Student stu = studentService.get(id);
//        for(Object[] o : classService.listClassNotRegist(id)) {
//            ClassCourseDto clco = new ClassCourseDto();
//            clco.setId(Integer.parseInt(String.valueOf(o[0])));
//            clco.setClassName((String) o[1]);
//            clco.setCourseName((String) o[2]);
//            list.add(clco);
//        }
//        mav.addObject("listclass", list);
//        mav.addObject("student", stu);
//        return mav;
//    }

    @PostMapping("/{studentId}/register")
    public String register(@PathVariable @RequestParam("studentId") Integer studentId,
                                 @RequestParam("classId") Integer[] classIds) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Classes> classes = classRepository.findAllById(Arrays.asList(classIds));
            student.setAssignClass(classes);
            studentRepository.save(student);
        }
        return "redirect:/student";
    }

    @GetMapping("/{stuId}/class/list")
    public ModelAndView listClassOfStudent(@PathVariable(name = "stuId") Integer id) {
        ModelAndView mav = new ModelAndView("listclassregisted");
        List<StudentClassDto> list = new ArrayList<>();
        Student stu = studentService.get(id);
        for(Object[] o : classService.listClassOfStudent(id)) {
            StudentClassDto clco = new StudentClassDto();
            clco.setClassId(Integer.parseInt(String.valueOf(o[0])));
            clco.setClassName(String.valueOf(o[1]));
            list.add(clco);
        }
        mav.addObject("listclass", list);
        mav.addObject("student", stu);
        return mav;
    }

    @DeleteMapping("/{studentId}/delete")
    public String deleteClassRegited(@PathVariable(name = "studentId") int studentId) {
        classService.deleteClassRegist(studentId);
        return "redirect:/student";
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return student;
    }
}
