package reservations;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/reservations")
    public String get(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo,
            Authentication auth) {
        if (reservationFrom != null && reservationTo != null
                && reservationRepository.findOverlappingReservations(reservationFrom, reservationTo).isEmpty()) {
            Account account = accountRepository.findByUsername(auth.getName());
            reservationRepository.save(new Reservation(account, reservationFrom, reservationTo));
        }

        return "redirect:/reservations";
    }

}
