package postredirectget;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostRedirectGetController {

    private List<String> list;

    public PostRedirectGetController() {
        this.list = new ArrayList<>();
    }

}
