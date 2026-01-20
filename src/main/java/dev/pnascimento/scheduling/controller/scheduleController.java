package dev.pnascimento.scheduling.controller;

import dev.pnascimento.scheduling.dto.schedule.ScheduleCreateRequest;
import dev.pnascimento.scheduling.entity.schedule.Scheduling;
import dev.pnascimento.scheduling.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class scheduleController {

    private final ScheduleService scheduleService;

    public scheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public Scheduling create(@Valid @RequestBody ScheduleCreateRequest request) {
        return scheduleService.create(request);
    }

    @PutMapping("/{id}")
    public Scheduling update(@RequestParam Long id, @Valid @RequestBody ScheduleCreateRequest request) {
        return scheduleService.update(id, request);
    }

    @PutMapping("/{id}/cancel")
    public Scheduling cancel(@RequestParam Long id) {
        return scheduleService.cancel(id);
    }

    @PutMapping("/{id}/conclude")
    public Scheduling conclude(@RequestParam Long id) {
        return scheduleService.conclude(id);
    }

    @GetMapping("/{id}")
    public Scheduling getById(@RequestParam Long id) {
        return scheduleService.getById(id);
    }
}
