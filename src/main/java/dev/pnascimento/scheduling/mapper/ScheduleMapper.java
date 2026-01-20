package dev.pnascimento.scheduling.mapper;

import dev.pnascimento.scheduling.dto.schedule.ScheduleCreateRequest;
import dev.pnascimento.scheduling.entity.schedule.Scheduling;
import dev.pnascimento.scheduling.entity.schedule.StatusScheduling;

public class ScheduleMapper {

    public static Scheduling toEntity(ScheduleCreateRequest req) {
        return Scheduling.builder()
                .name(req.name())
                .description(req.description())
                .startDate(req.startDate())
                .endDate(req.endDate())
                .userId(req.userId())
                .status(StatusScheduling.SCHEDULED)
                .build();
    }
}
