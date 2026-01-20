package dev.pnascimento.scheduling.controller;

import dev.pnascimento.scheduling.dto.user.UserCreateRequest;
import dev.pnascimento.scheduling.dto.user.UserResponse;
import dev.pnascimento.scheduling.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class userController {

    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    public UserResponse update(@RequestParam Long id, @Valid @RequestBody UserCreateRequest request) {
        return userService.updateUser(id, request);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/by-email")
    public UserResponse getByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
