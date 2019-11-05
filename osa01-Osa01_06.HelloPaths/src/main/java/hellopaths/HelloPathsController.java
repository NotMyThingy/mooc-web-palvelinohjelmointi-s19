package hellopaths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloPathsController {

    @GetMapping("/hello")
    @ResponseBody
    public String Hello() {
        return "Hello";
    }

    @GetMapping("/paths")
    @ResponseBody
    public String Paths() {
        return "Paths";
    }

}
