package dev.pnascimento.scheduling.dto.schedule;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ScheduleUpdateRequest(
        @Size(max = 100) String name,
        @Size (max = 500) String description,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
