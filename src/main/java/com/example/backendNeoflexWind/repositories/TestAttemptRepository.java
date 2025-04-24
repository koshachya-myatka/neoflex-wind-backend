package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.TestAttempt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TestAttemptRepository extends JpaRepository<TestAttempt, Long> {
    List<TestAttempt> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("INSERT INTO test_attempts (user_id, era, attempts_used, last_attempt) " +
            "VALUES (:userId, :era, 1, CURRENT_TIMESTAMP) " +
            "ON CONFLICT (user_id, era) DO UPDATE SET attempts_used = LEAST(EXCLUDED.attempts_used + 1, 3), last_attempt = CURRENT_TIMESTAMP")
    void incrementAttempt(@Param("userId") Long userId, @Param("era") String era);
}
