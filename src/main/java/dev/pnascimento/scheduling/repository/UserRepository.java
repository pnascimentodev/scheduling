package dev.pnascimento.scheduling.repository;

import dev.pnascimento.scheduling.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository <User, Long> {

    @Query("""
            SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END
            FROM User u
            WHERE u.email = :email
              AND (:excludeId IS NULL OR u.id <> :excludeId)
            """)

    boolean existConflict(
            @Param("email") String email,
            @Param("excludeId") Long excludeId
    );

}

