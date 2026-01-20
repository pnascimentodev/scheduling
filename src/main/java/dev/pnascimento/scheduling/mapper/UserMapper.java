package dev.pnascimento.scheduling.mapper;

import dev.pnascimento.scheduling.dto.user.UserCreateRequest;
import dev.pnascimento.scheduling.dto.user.UserResponse;
import dev.pnascimento.scheduling.entity.user.User;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toEntity(UserCreateRequest req) {
        return User.builder()
                .name(req.name())
                .email(req.email())
                .password(req.password())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static void merge(User entity, @Valid UserCreateRequest req) {
        if (req.name() != null) {
            entity.setName(req.name());
        }
        if (req.email() != null) {
            entity.setEmail(req.email());
        }
        if (req.password() != null) {
            entity.setPassword(req.password());
        }
        entity.setUpdatedAt(LocalDateTime.now());
    }

    public static UserResponse toResponse(User a) {
        if (a == null) return null;
        return new UserResponse(
                a.getId(),
                a.getName(),
                a.getEmail()
        );
    }
}
