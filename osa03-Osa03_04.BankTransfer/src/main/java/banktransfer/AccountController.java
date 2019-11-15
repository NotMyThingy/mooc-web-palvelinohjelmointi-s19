package banktransfer;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct

    public void init() {

        // test data to the application
        Account account = new Account();
        account.setIban("0000");
        account.setBalance(1000);
        accountRepository.save(account);

        Account account2 = new Account();
        account2.setIban("0001");
        account2.setBalance(500);
        accountRepository.save(account2);

        Account account3 = new Account();
        account3.setIban("0002");
        account3.setBalance(50);
        accountRepository.save(account3);
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("accounts", this.accountRepository.findAll());
        return "index";
    }

    @Transactional
    @PostMapping("/")
    public String transfer(@RequestParam String from, @RequestParam String to, @RequestParam Integer amount) {
        accountRepository
                .findByIban(from)
                .setBalance(accountRepository
                        .findByIban(from)
                        .getBalance() - amount);

        accountRepository
                .findByIban(to)
                .setBalance(accountRepository
                        .findByIban(to)
                        .getBalance() + amount);

        return "redirect:/";
    }
}
