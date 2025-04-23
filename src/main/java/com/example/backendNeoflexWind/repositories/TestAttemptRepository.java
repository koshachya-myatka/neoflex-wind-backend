package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.TestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestAttemptRepository extends JpaRepository<TestAttempt, Long> {
}
