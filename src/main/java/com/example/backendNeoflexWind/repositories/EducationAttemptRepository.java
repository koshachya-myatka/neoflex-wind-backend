package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.EducationAttempt;
import com.example.backendNeoflexWind.models.TestAttempt;
import com.example.backendNeoflexWind.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationAttemptRepository extends JpaRepository<EducationAttempt, Long> {
    List<EducationAttempt> findByUserId(Long userId);

    int countByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO education_attempts (user_id, attempts_used) " +
            "VALUES (:userId, :attemptsUsed);", nativeQuery = true)
    void incrementAttempt(@Param("userId") Long userId, @Param("attemptsUsed") int attemptsUsed);
}
