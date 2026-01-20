package dev.pnascimento.scheduling.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Data to create or update a user")
public record UserCreateRequest(
        @Schema(description = "User's full name", example = "John Silva")
        @NotBlank @Size(max = 100) String name,

        @Schema(description = "Unique user email", example = "john@example.com")
        @NotBlank @Email String email,

        @Schema(description = "User password (minimum 6 characters)", example = "password123")
        @NotBlank String password
        ) {
}
