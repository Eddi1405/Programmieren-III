package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.UserRepository;
import de.trio.imageshare.web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Dieser Controller ist für die Registrierung der User zuständig.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepo;
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Speichert die im Formular eingegebenen Daten und speichert diese als neue UserDaten in der Datenbank ab.
     *
     * @return
     */
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @GetMapping("/register_error")
    public String showRegister_error() {
        return "register_error";
    }

    /**
     * Der gespeicherte User wird übergeben und das Eingegebene Password wird verschlüsselt in der DB gespeichert.
     * Ebenfalls wird überprüft, ob es der jeweilige User oder die E-Mail schon existiert.
     * Um jeden Usernamen und jede E-Mail einzigartig zu halten.
     *
     * @return
     */
    @PostMapping("/process_register")
    public String processRegister(@RequestParam("user") String name, @RequestParam("email") String email, @RequestParam("password") String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        if (!userRepo.existsByUsername(name) && !userRepo.existsByEmail(email)) {
            userService.saveData(encodedPassword, name, email);
            return "redirect:/login";

        } else {
            return "register_error";
        }

    }
}
