package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.entities.UserDaten;
import de.trio.imageshare.web.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Dieser Controller ist für die Registrierung der User zuständig.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepo;

    /**
     * Gibt die Indexseite zurück.
     * @return
     */
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    /**
     * Speichert die im Formular eingegebenen Daten und speichert diese als neue UserDaten in der Datenbank ab.
     * @param model
     * @return
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDaten());

        return "signup_form";
    }

    /**
     * Der gespeicherte User wird übergeben und das Eingegebene Password wird verschlüsselt in der DB gespeichert.
     * Ebenfalls wird überprüft, ob es der jeweilige User oder die E-Mail schon existiert.
     * Um jeden Usernamen und jede E-Mail einzigartig zu halten.
     * @param user
     * @return
     */
    @PostMapping("/process_register")
    public String processRegister(UserDaten user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if(!userRepo.existsByUsername(user.getUsername())&& !userRepo.existsByEmail(user.getEmail())){
            userRepo.save(user);
            return "register_success";

        }else{
            return "register_error";
        }

    }
}
