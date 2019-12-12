package weatherservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations")
    public String list(Model model) {
        model.addAttribute("locations", locationService.getLocations());
        return "locations";
    }

    @GetMapping("/locations/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("location", locationService.getLocation(id));
        return "location";
    }

    @PostMapping("/locations")
    @CacheEvict(value = "locations", allEntries = true)
    public String add(@ModelAttribute Location location) {
        locationRepository.save(location);
        return "redirect:/locations";
    }

}
