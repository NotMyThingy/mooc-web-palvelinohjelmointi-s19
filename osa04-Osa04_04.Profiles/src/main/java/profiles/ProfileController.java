package profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

    @Value("${spring.profiles.active:unknown}")
    private String activeProfile;

    @ResponseBody
    @GetMapping("/profile")
    public String getProfile() {
        return activeProfile;
    }

    public String home() {
        return "index";
    }
}
