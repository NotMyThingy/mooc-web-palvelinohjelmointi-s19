package square;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SquareController {

    @GetMapping("/square")
    @ResponseBody
    public String home(@RequestParam Integer num) {
        return String.valueOf(num * num);
    }
}
