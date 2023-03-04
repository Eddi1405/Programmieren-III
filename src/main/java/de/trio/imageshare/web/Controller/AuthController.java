package de.trio.imageshare.web.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;
import lombok.Data;
@Slf4j
public class AuthController {
    //Loginrequest Routing
    @GetMapping("/login")
    public String onOpenUrl() {
        log.info("entering onOpenUrl (GET-Method /login)");
        return "login";}
}







