package de.trio.imageshare.web.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AuthController {
    //Loginrequest Routing
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}