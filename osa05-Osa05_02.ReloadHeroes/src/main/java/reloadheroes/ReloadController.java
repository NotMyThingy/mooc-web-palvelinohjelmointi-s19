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
        ReloadStatus rs = null;

        if (session.getAttribute("name") != null) {
            String name = (String) session.getAttribute("name");
            rs = reloadStatusRepository.findByName(name);
        }

        if (rs == null) {
            rs = new ReloadStatus(UUID.randomUUID().toString(), 0);
            session.setAttribute("name", rs.getName());
        }

        rs.setReloads(rs.getReloads() + 1);

        rs = reloadStatusRepository.save(rs);
        model.addAttribute("status", rs);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("reloads").descending());
        model.addAttribute("scores", reloadStatusRepository.findAll(pageable).getContent());

        return "index";
    }
}
