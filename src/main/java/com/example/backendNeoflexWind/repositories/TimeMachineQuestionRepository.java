package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.TimeMachineQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimeMachineQuestionRepository extends JpaRepository<TimeMachineQuestion, Long> {
}
