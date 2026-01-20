package dev.pnascimento.scheduling.mapper;

import dev.pnascimento.scheduling.dto.user.UserCreateRequest;
import dev.pnascimento.scheduling.dto.user.UserUpdateRequest;
import dev.pnascimento.scheduling.entity.user.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toEntity(UserCreateRequest req) {
        return User.builder()
                .name(req.name())
                .email(req.email())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static void merge(User entity, UserUpdateRequest req) {
        if (req.name() != null) {
            entity.setName(req.name());
        }
        if (req.email() != null) {
            entity.setEmail(req.email());
        }
    }

    public static User toResponse(User a) {
        return new User(
                a.getId(),
                a.getName(),
                a.getEmail(),
                a.getPassword(),
                a.getCreatedAt(),
                a.getUpdatedAt()
        );
    }
}
