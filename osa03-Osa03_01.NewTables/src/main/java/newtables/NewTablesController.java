package newtables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author notmythingy
 */
@Controller
public class NewTablesController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("course", courseRepository.findAll());
        return "index";
    }

}
