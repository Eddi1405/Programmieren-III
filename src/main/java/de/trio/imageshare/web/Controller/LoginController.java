package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.UserRepository;
import de.trio.imageshare.web.entities.UserDaten;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Der LoginController ist für die Verarbeitung der Anmeldeinformation zuständig.
 */
@Controller
public class LoginController {
    public String user = "test";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * GetMapping login gibt bei Aufruf des Pfades das LoginFormular wieder.
     * @return
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    /**
     * processLogin(), die auf einen POST-Aufruf auf "/login" reagiert und die empfangenen Anmeldeinformationen verarbeitet.
     * Die Methode ruft das userRepository auf, um das Benutzerobjekt anhand des Benutzernamens abzurufen, und prüft dann mithilfe des passwordEncoder,
     * ob das eingegebene Passwort mit dem in der Datenbank gespeicherten übereinstimmt.
     * Wenn dies der Fall ist, wird eine Sitzungsvariable für den Benutzer gesetzt und der Benutzer zur "dashboard" -Seite weitergeleitet.
     * Wenn die Anmeldedaten nicht korrekt sind, wird der Benutzer zur "login" -Seite zurückgeleitet und eine Fehlermeldung wird angezeigt.
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session) {

        // Überprüfung der Anmeldeinformationen
        Optional<UserDaten> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            UserDaten user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute("user", user);
                return "redirect:/dashboard";
            }
        }

        return "redirect:/login?error";
    }
}
