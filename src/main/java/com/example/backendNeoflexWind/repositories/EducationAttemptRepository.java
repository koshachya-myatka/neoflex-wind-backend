package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.EducationAttempt;
import com.example.backendNeoflexWind.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationAttemptRepository extends JpaRepository<EducationAttempt, Long> {
    Optional<EducationAttempt> findByUserId(Long userId);
    Optional<EducationAttempt> findByUser(User user);
}
