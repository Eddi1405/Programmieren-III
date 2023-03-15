package de.trio.imageshare.web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    LoginController loginController;

    public LogoutController(LoginController loginController) {
        this.loginController = loginController;
    }

    /**
     * Gibt den Logout aus
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String showLogout(HttpSession session) {
        session.invalidate(); //Session löschen
        loginController.user = null;
        return "redirect:/register_success"; //Login-Seite mit Bestätigungsnachricht aufrufen
    }
}
