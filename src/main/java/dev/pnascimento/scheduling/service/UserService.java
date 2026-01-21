package dev.pnascimento.scheduling.service;

import dev.pnascimento.scheduling.dto.user.UserCreateRequest;
import dev.pnascimento.scheduling.dto.user.UserResponse;
import dev.pnascimento.scheduling.entity.user.User;
import dev.pnascimento.scheduling.mapper.UserMapper;
import dev.pnascimento.scheduling.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(@Valid UserCreateRequest req) {

        conflictChecker(req.email(), req.name(), null);

        String encodedPassword = passwordEncoder.encode(req.password());

        User user = UserMapper.toEntity(req, encodedPassword);
        user = userRepo.save(user);
        return UserMapper.toResponse(user);

    }

    public UserResponse updateUser(Long id, @Valid UserCreateRequest req) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        conflictChecker(req.email(), req.name(), id);

        String encodedPassword = passwordEncoder.encode(req.password());

        UserMapper.merge(user, req, encodedPassword);
        user.setUpdatedAt(LocalDateTime.now());

        user = userRepo.save(user);
        return UserMapper.toResponse(user);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserMapper.toResponse(user);
    }

    public UserResponse getUserByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserMapper.toResponse(user);
    }

    private void conflictChecker(String email, String username, Long excludeId) {
        if (userRepo.existConflict(email, excludeId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
        if (userRepo.existUsernameConflict(username, excludeId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already in use");
        }
    }


}
