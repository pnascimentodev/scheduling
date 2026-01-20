package dev.pnascimento.scheduling.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User data returned")
public record UserResponse(
        @Schema(description = "User's unique ID", example = "1")
        Long id,

        @Schema(description = "User's full name", example = "John Silva")
        String name,

        @Schema(description = "User email", example = "john@example.com")
        String email
) {
}
