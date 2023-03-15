package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.UserRepository;
import de.trio.imageshare.web.entities.UserDaten;
import jakarta.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpSession httpSession;

    @Mock
    private Model model;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private LoginController loginController;

    private String username;
    private String password;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        username = "testUser";
        password = "testPassword";
    }

    @Test
    public void testShowLogin() {
        assertEquals("login", loginController.showLogin(model, httpSession));
    }

    @Test
    public void testProcessLoginWithCorrectCredentials() {
        UserDaten user = new UserDaten();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(password, user.getPassword())).thenReturn(true);

        assertEquals("redirect:/dashboard", loginController.processLogin(username, password, httpSession));
        verify(httpSession).setAttribute("user", username);
    }

    @Test
    public void testProcessLoginWithIncorrectCredentials() {
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertEquals("redirect:/login_error", loginController.processLogin(username, password, httpSession));

        UserDaten user = new UserDaten();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode("wrongPassword"));
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(password, user.getPassword())).thenReturn(false);

        assertEquals("redirect:/login_error", loginController.processLogin(username, password, httpSession));
    }

    @Test
    public void testShowLogin_error() {
        assertEquals("login_error", loginController.showLogin_error(model, httpSession));
    }

    @Test
    public void testNavbarWhenUserIsLoggedIn() {
        when(httpSession.getAttribute("user")).thenReturn(username);
        loginController.navbar(model, httpSession);
        verify(model).addAttribute(eq("log"), eq("/fragments/topnavbarLog"));
    }

    @Test
    public void testNavbarWhenUserIsNotLoggedIn() {
        when(httpSession.getAttribute("user")).thenReturn(null);
        loginController.navbar(model, httpSession);
        verify(model).addAttribute(eq("log"), eq("/fragments/topnavbar"));
    }
}
