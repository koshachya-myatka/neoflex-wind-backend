package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.EducationAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationAnswerRepository extends JpaRepository<EducationAnswer, Long> {
    Optional<EducationAnswer> findByUserIdAndItemId(Long userId, Long itemId);
}
