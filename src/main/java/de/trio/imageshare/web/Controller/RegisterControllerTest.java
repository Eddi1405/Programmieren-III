package de.trio.imageshare.web.Controller;

import de.trio.imageshare.web.Repository.UserRepository;
import de.trio.imageshare.web.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class RegisterControllerTest {

    private RegisterController controller;
    private UserRepository userRepo;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepo = mock(UserRepository.class);
        userService = mock(UserService.class);
        controller = new RegisterController(userService);
        controller.userRepo = userRepo;
    }


    @Test
    void testProcessRegisterWithExistingUser() {
        String name = "existingUser";
        String email = "existinguser@example.com";
        String password = "password";

        when(userRepo.existsByUsername(name)).thenReturn(true);

        String viewName = controller.processRegister(name, email, password);

        verify(userService, never()).saveData(anyString(), anyString(), anyString());
        assertEquals("register_error", viewName);
    }

}
