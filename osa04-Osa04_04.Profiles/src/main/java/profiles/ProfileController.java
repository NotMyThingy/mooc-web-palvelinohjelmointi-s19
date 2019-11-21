package profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {


    @ResponseBody
    @GetMapping("/profile")
    public String getProfile() {
        return "Hello world!";
    }

    public String home() {
        return "index";
    }

}
