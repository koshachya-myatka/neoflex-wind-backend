package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
}
