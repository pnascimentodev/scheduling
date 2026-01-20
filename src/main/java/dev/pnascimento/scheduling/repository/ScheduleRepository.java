package dev.pnascimento.scheduling.repository;

import dev.pnascimento.scheduling.entity.schedule.Scheduling;
import dev.pnascimento.scheduling.entity.schedule.StatusScheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ScheduleRepository extends JpaRepository<Scheduling, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
            FROM Scheduling a
            WHERE a.userId = :userId
              AND a.status = 'SCHEDULED'
              AND (:excludeId IS NULL OR a.id <> :excludeId)
              AND (
                (a.startDate <= :endDate AND a.endDate >= :startDate)
              )
            """)

    boolean existConflict(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("excludeId") Long excludeId
    );
}
