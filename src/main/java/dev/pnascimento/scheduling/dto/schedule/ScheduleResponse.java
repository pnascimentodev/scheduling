package dev.pnascimento.scheduling.dto.schedule;

import java.time.LocalDateTime;

public record ScheduleResponse(
        Long id,
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long userId,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
