package airports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private AircraftRepository aircraftRepository;

    @GetMapping("/airports")
    public String list(Model model) {
        model.addAttribute("airports", airportRepository.findAll());
        return "airports";
    }

    @PostMapping("/airports")
    public String create(@RequestParam String identifier, @RequestParam String name) {
        Airport a = new Airport();
        a.setIdentifier(identifier);
        a.setName(name);

        airportRepository.save(a);
        return "redirect:/airports";
    }

    @PostMapping("/aircrafts/{aircraftId}/airports")
    public String assignAirport(@PathVariable Long aircraftId, @RequestParam Long airportId) {
        Aircraft aircraft = aircraftRepository.getOne(aircraftId);
        Airport airport = airportRepository.getOne(airportId);

        aircraft.setAirport(airport);

        aircraftRepository.save(aircraft);

        return "redirect:/aircrafts";
    }
}
