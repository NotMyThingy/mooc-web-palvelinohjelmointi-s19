package helloindividualpages;

import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloIndividualPagesController {

    private Map<String, Item> items;

    public HelloIndividualPagesController() {
        this.items = new TreeMap<>();
        Item item = new Item("Wizard hat", "pointy");
        this.items.put(item.getIdentifier(), item);
    }

    @PostMapping("/")
    public String post(@RequestParam String name, @RequestParam String type) {
        Item item = new Item(name, type);
        this.items.put(item.getIdentifier(), item);
        return "redirect:/";
    }

    @GetMapping("/{item}")
    public String single(Model model, @PathVariable String item) {
        if (!this.items.containsKey(item)) {
            return "redirect:/";
        }

        model.addAttribute("item", this.items.get(item));
        return "single";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", this.items.values());
        return "index";
    }
}
