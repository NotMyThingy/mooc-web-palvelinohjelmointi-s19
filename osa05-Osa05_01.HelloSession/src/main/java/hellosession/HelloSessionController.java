package hellosession;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloSessionController {

    @RequestMapping("*")
    @ResponseBody
    public String sayHello(HttpSession session) {
        if (session.getAttribute("myFirstRodeo") != null) {
            return "Hello again!";
        }

        session.setAttribute("myFirstRodeo", "No way, Jose!");
        return "Hello there!";
    }
}
