package nttdata.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nttdata.demo.exception.CustomException;
import nttdata.demo.model.*;
import nttdata.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping(value = "/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        try{
            return userService.Login(loginRequest);
        }
        catch (Exception ex) {
            log.error("Login error: " + ex.getMessage());
            throw new CustomException(HttpStatus.BAD_REQUEST.name(),ex.getMessage());
        }
    }
    
    @PostMapping(value = "/create")
    public UserResponse create(@Valid @RequestBody UserRequest userRequest) {
		try {
			return userService.create(userRequest);
		}
		catch (Exception ex) {
			log.error("Login error: " + ex.getMessage());
            throw new CustomException(HttpStatus.BAD_REQUEST.name(),ex.getMessage());
		}
    }
}
