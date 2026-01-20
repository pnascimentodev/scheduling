package dev.pnascimento.scheduling.service;

import dev.pnascimento.scheduling.dto.user.UserCreateRequest;
import dev.pnascimento.scheduling.dto.user.UserResponse;
import dev.pnascimento.scheduling.entity.user.User;
import dev.pnascimento.scheduling.mapper.UserMapper;
import dev.pnascimento.scheduling.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserResponse createUser(@Valid UserCreateRequest req) {

        conflictChecker(req.email());

        User user = UserMapper.toEntity(req);
        user = userRepo.save(user);
        return UserMapper.toResponse(user);

    }

    public UserResponse updateUser(Long id, @Valid UserCreateRequest req) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        conflictChecker(req.email());

        UserMapper.merge(user, req);
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

    private void conflictChecker(String email) {
        boolean exists = userRepo.existConflict(email, null);
        if (exists) {
            throw new IllegalArgumentException("Email already in use");
        }
    }


}
