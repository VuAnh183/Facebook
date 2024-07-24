package nttdata.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nttdata.demo.model.UserRequest;
import nttdata.demo.model.UserResponse;
import nttdata.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/list")
    @PreAuthorize("hasAnyRole('admin','system')")
    public List<UserResponse> UserList() {
        return userService.GetList();
    }

    @PreAuthorize("hasRole('admin') or hasPermission('CREATE')")
    @PostMapping("/create")
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }
}
