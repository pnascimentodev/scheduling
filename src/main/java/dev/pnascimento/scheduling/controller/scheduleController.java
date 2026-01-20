package dev.pnascimento.scheduling.controller;

import dev.pnascimento.scheduling.dto.schedule.ScheduleCreateRequest;
import dev.pnascimento.scheduling.dto.schedule.ScheduleResponse;
import dev.pnascimento.scheduling.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
@Tag(name = "Schedules", description = "API to manage schedules")
public class scheduleController {

    private final ScheduleService scheduleService;

    public scheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    @Operation(summary = "Create new schedule", description = "Creates a new schedule in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ScheduleResponse create(@Valid @RequestBody ScheduleCreateRequest request) {
        return scheduleService.createSchedule(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update schedule", description = "Updates the data of an existing schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
            @ApiResponse(responseCode = "404", description = "Schedule not found")
    })
    public ScheduleResponse update(@RequestParam Long id, @Valid @RequestBody ScheduleCreateRequest request) {
        return scheduleService.updateSchedule(id, request);
    }

    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancel schedule", description = "Cancels an existing schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule cancelled successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found")
    })
    public ScheduleResponse cancel(@RequestParam Long id) {
        return scheduleService.cancelSchedule(id);
    }

    @PutMapping("/{id}/conclude")
    @Operation(summary = "Conclude schedule", description = "Marks a schedule as completed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule completed successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found")
    })
    public ScheduleResponse conclude(@RequestParam Long id) {
        return scheduleService.concludeSchedule(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get schedule by ID", description = "Returns the data of a specific schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schedule found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleResponse.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found")
    })
    public ScheduleResponse getById(@RequestParam Long id) {
        return scheduleService.getScheduleById(id);
    }
}
