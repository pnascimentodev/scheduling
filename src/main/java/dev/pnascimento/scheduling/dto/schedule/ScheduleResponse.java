package dev.pnascimento.scheduling.dto.schedule;

import java.time.LocalDateTime;

public record ScheduleResponse(
        Long id,
        String name,
        String description,
        String startDate,
        String endDate,
        Long userId,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
