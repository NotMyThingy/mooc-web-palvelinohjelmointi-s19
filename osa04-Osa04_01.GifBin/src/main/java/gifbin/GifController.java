package gifbin;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/gifs")
    public String gifs() {
        return "redirect:/gifs/1";
    }

    @GetMapping("/gifs/{id}")
    public String gifs(Model model, @PathVariable Long id) {
        model.addAttribute("count", gifRepository.count());
        model.addAttribute("current",
                gifRepository.existsById(id)
                ? id
                : null);
        model.addAttribute("previous",
                gifRepository.existsById(id - 1)
                ? id - 1
                : null);
        model.addAttribute("next",
                gifRepository.existsById(id + 1)
                ? id + 1
                : null);

        return "gifs";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] getGif(@PathVariable Long id) {
        return gifRepository.getOne(id).getContent();
    }

    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().equals("image/gif")) {
            Gif gif = new Gif();
            gif.setContent(file.getBytes());
            gifRepository.save(gif);
        }

        return "redirect:/gifs";
    }
}
