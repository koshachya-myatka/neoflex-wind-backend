package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.EducationAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationAttemptRepository extends JpaRepository<EducationAttempt, Long> {
}
