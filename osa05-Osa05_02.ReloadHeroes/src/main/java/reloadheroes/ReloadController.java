package reloadheroes;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {
        ReloadStatus status = null;

        if (session.getAttribute("name") != null) {
            String name = (String) session.getAttribute("name");
            status = reloadStatusRepository.findByName(name);
        }

        if (status == null) {
            status = new ReloadStatus("User" + UUID.randomUUID().toString().substring(0, 6), 0);
            session.setAttribute("name", status.getName());
        }

        status.setReloads(status.getReloads() + 1);

        reloadStatusRepository.save(status);
        model.addAttribute("status", status);

        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "reloads");
        model.addAttribute("scores", reloadStatusRepository.findAll(pageable));

        return "index";
    }
}
