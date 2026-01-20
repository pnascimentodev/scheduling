package dev.pnascimento.scheduling.dto.schedule;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Schedule data returned")
public record ScheduleResponse(
        @Schema(description = "Schedule's unique ID", example = "1")
        Long id,

        @Schema(description = "Schedule name/title", example = "Planning meeting")
        String name,

        @Schema(description = "Detailed schedule description", example = "Meeting with the development team")
        String description,

        @Schema(description = "Start date and time", example = "2026-02-01T10:00:00")
        LocalDateTime startDate,

        @Schema(description = "End date and time", example = "2026-02-01T11:00:00")
        LocalDateTime endDate,

        @Schema(description = "ID of responsible user", example = "1")
        Long userId,

        @Schema(description = "Schedule status", example = "SCHEDULED", allowableValues = {"SCHEDULED", "CANCELLED", "CONCLUDED"})
        String status,

        @Schema(description = "Creation date and time", example = "2026-01-19T10:00:00")
        LocalDateTime createdAt,

        @Schema(description = "Last update date and time", example = "2026-01-19T10:00:00")
        LocalDateTime updatedAt
) {
}
