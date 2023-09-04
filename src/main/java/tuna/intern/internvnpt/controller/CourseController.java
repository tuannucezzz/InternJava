package tuna.intern.internvnpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tuna.intern.internvnpt.domain.Course;
import tuna.intern.internvnpt.domain.Student;
import tuna.intern.internvnpt.dto.CourseDto;
import tuna.intern.internvnpt.repository.CourseRepository;
import tuna.intern.internvnpt.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/addcourse")
    public String addCourse(Model model) {
        List<CourseDto> listcourse = courseService.listAll();
        model.addAttribute("listcourse", listcourse);
        model.addAttribute("course", new Course());
        return "addcourse";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCourse(@ModelAttribute("course") CourseDto course, RedirectAttributes ra) {
        courseService.createCourse(course);
        ra.addFlashAttribute("success_message", "The course has been saved successfully.");
        return "redirect:/course";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditCoursePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("addcourse");
        Course course = courseService.get(id);
        mav.addObject("course", course);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deleteCoursePage(@PathVariable(name = "id") int id, RedirectAttributes ra) {
        courseService.delete(id);
        ra.addFlashAttribute("error_message", "The course has been deleted successfully.");
        return "redirect:/course";
    }
}
