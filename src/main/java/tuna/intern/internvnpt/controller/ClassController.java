package tuna.intern.internvnpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tuna.intern.internvnpt.domain.Classes;
import tuna.intern.internvnpt.domain.Course;
import tuna.intern.internvnpt.dto.CourseDto;
import tuna.intern.internvnpt.service.ClassService;
import tuna.intern.internvnpt.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/addclass")
    public String addCourse(Model model) {

        List<CourseDto> listcourse = courseService.listAll();
        List<Classes> listclass = classService.listAll();
        model.addAttribute("listclass", listclass);

        model.addAttribute("listcourse", listcourse);
        model.addAttribute("class", new Classes());
        return "addclass";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveClass(@ModelAttribute("class") Classes classes, RedirectAttributes ra) {
        classService.save(classes);
        ra.addFlashAttribute("success_message", "The class has been saved successfully.");
        return "redirect:/class";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditClassPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("addclass");
        List<CourseDto> listcourse = courseService.listAll();
        Classes cls = classService.get(id);
        mav.addObject("listcourse", listcourse);
        mav.addObject("class", cls);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteClassPage(@PathVariable(name = "id") int id, RedirectAttributes ra) {
        classService.delete(id);
        ra.addFlashAttribute("error_message", "The class has been deleted successfully.");
        return "redirect:/class";
    }
}
