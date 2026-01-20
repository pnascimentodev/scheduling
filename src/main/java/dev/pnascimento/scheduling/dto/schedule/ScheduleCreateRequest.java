package dev.pnascimento.scheduling.dto.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Schema(description = "Data to create or update a schedule")
public record ScheduleCreateRequest(
        @Schema(description = "Schedule name/title", example = "Planning meeting")
        @NotBlank @Size (max = 100) String name,

        @Schema(description = "Detailed schedule description", example = "Meeting with the development team")
        @Size (max = 500) String description,

        @Schema(description = "Start date and time", example = "2026-02-01T10:00:00")
        @NotNull LocalDateTime startDate,

        @Schema(description = "End date and time", example = "2026-02-01T11:00:00")
        @NotNull LocalDateTime endDate,

        @Schema(description = "ID of responsible user", example = "1")
        @NotNull Long userId
) {


}
