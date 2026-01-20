package dev.pnascimento.scheduling.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public record UserCreateRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @UniqueElements String email,
        @NotBlank String password
        ) {
}
