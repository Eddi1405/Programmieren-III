package de.trio.imageshare.web.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;
import lombok.Data;
@Slf4j
public class RegisterController {
    //Loginrequest Routing
    @GetMapping("/register")
    public String onOpenUrl() {
        log.info("entering onOpenUrl (GET-Method /register)");
        return "register";
    }
}
