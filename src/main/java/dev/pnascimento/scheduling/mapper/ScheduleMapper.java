package dev.pnascimento.scheduling.mapper;

import dev.pnascimento.scheduling.dto.schedule.ScheduleCreateRequest;
import dev.pnascimento.scheduling.entity.schedule.Scheduling;
import dev.pnascimento.scheduling.entity.schedule.StatusScheduling;

import java.time.LocalDateTime;

public class ScheduleMapper {

    public static Scheduling toEntity(ScheduleCreateRequest req) {
        return Scheduling.builder()
                .name(req.name())
                .description(req.description())
                .startDate(req.startDate())
                .endDate(req.endDate())
                .userId(req.userId())
                .status(StatusScheduling.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static void merge(Scheduling entity, ScheduleCreateRequest req) {
        if (req.name() != null) {
            entity.setName(req.name());
        }
        if (req.description() != null) {
            entity.setDescription(req.description());
        }
        if (req.startDate() != null) {
            entity.setStartDate(req.startDate());
        }
        if (req.endDate() != null) {
            entity.setEndDate(req.endDate());
        }
    }

    public static Scheduling toResponse(Scheduling a) {
        return new Scheduling(
                a.getId(),
                a.getName(),
                a.getDescription(),
                a.getStartDate(),
                a.getEndDate(),
                a.getStatus(),
                a.getUserId(),
                a.getCreatedAt(),
                a.getUpdatedAt()
        );
    }
}
