package filemanager;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    @Autowired
    private FileObjectRepository fileObjectRepository;

    @GetMapping("/files")
    public String home(Model model) {
        model.addAttribute("files", fileObjectRepository.findAll());

        return "files";
    }

    @PostMapping("/files")
    public String add(@RequestParam("file") MultipartFile file) throws IOException {
        FileObject fileObject = new FileObject();

        fileObject.setName(file.getOriginalFilename());
        fileObject.setContentType(file.getContentType());
        fileObject.setContentLength(file.getSize());
        fileObject.setContent(file.getBytes());

        fileObjectRepository.save(fileObject);

        return "redirect:/files";
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        FileObject fo = fileObjectRepository.getOne(id);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fo.getContentType()));
        headers.setContentLength(fo.getContentLength());
        headers.add("Content-Disposition", "attachement; filename=" + fo.getName());

        return new ResponseEntity<>(fo.getContent(), headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/files/{id}")
    public String delete(@PathVariable Long id) {
        fileObjectRepository.deleteById(id);

        return "redirect:/files";
    }
}
