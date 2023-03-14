package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.PictureRepository;
import de.trio.imageshare.web.Service.IndexService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    LoginController loginController;

    public LogoutController(LoginController loginController) {
        this.loginController = loginController;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //Session löschen
        loginController.user = null;
        return "redirect:/register_success"; //Login-Seite mit Bestätigungsnachricht aufrufen
    }
}
