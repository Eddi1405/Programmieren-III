package de.trio.imageshare.web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //Session löschen
        return "redirect:/register_success"; //Login-Seite mit Bestätigungsnachricht aufrufen
    }
}
