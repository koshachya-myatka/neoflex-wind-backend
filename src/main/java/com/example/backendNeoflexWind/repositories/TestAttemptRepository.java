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

    int countByUserIdAndEra(Long userId, String era);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO test_attempts (user_id, era, attempts_used) " +
            "VALUES (:userId, :era, :attemptsUsed);", nativeQuery = true)
    void incrementAttempt(@Param("userId") Long userId, @Param("era") String era, @Param("attemptsUsed") int attemptsUsed);
}
