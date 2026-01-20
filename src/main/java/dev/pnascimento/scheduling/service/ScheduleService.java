package dev.pnascimento.scheduling.service;

import dev.pnascimento.scheduling.dto.schedule.ScheduleCreateRequest;
import dev.pnascimento.scheduling.entity.schedule.Scheduling;
import dev.pnascimento.scheduling.entity.schedule.StatusScheduling;
import dev.pnascimento.scheduling.mapper.ScheduleMapper;
import dev.pnascimento.scheduling.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

public class ScheduleService {

    private final ScheduleRepository repo;

    public ScheduleService(ScheduleRepository repo) {
        this.repo = repo;
    }

    public Scheduling create(@Valid ScheduleCreateRequest req){

        gapValidator(req.startDate(), req.endDate());
        conflictChecker(req.userId(), req.startDate(), req.endDate(), null);

        Scheduling entity = ScheduleMapper.toEntity(req);
        entity = repo.save(entity);
        return ScheduleMapper.toResponse(entity);
    }

    @Transactional
    public Scheduling update(Long id, @Valid ScheduleCreateRequest req){

        Scheduling entity = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Scheduling not found"));

        gapValidator(req.startDate(), req.endDate());
        conflictChecker(entity.getUserId(), req.startDate(), req.endDate(), entity.getId());

        ScheduleMapper.merge(entity, req);
        entity.setUpdatedAt(LocalDateTime.now());

        entity = repo.save(entity);
        return ScheduleMapper.toResponse(entity);
    }

    public Scheduling cancel(Long id) {
        Scheduling entity = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Scheduling not found"));

        entity.setStatus(StatusScheduling.CANCELLED);
        entity.setUpdatedAt(LocalDateTime.now());
        entity = repo.save(entity);

        return  ScheduleMapper.toResponse(entity);
    }

    public Scheduling conclude(Long id) {
        Scheduling entity = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Scheduling not found"));
        entity.setStatus(StatusScheduling.CONCLUDED);
        entity.setUpdatedAt(LocalDateTime.now());
        entity = repo.save(entity);

        return  ScheduleMapper.toResponse(entity);
    }

    public Scheduling getById(Long id) {
        Scheduling entity = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Scheduling not found"));
        return ScheduleMapper.toResponse(entity);
    }

    private void gapValidator(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null || !start.isBefore(end)) {
        throw new IllegalArgumentException("Invalid date range: start date must be before end date.");
        }
    }

    private void conflictChecker(Long userId, LocalDateTime start, LocalDateTime end, Long excludeId) {
        boolean hasConflict = repo.existConflict(userId, start, end, excludeId);
        if (hasConflict) {
            throw new IllegalStateException("Scheduling conflict detected! There is already an appointment scheduled for that time.");
    }
}

}
