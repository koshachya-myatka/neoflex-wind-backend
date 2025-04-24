package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.EducationAnswer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationAnswerRepository extends JpaRepository<EducationAnswer, Long> {
    Optional<EducationAnswer> findByUserIdAndItemId(Long userId, Long itemId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO education_answers (user_id, item_id, selected_category, is_correct) " +
            "VALUES (:userId, :itemId, :selected_category, :isCorrect);", nativeQuery = true)
    void saveUserAnswer(@Param("userId") Long userId, @Param("itemId") Long itemId,
                        @Param("selected_category") String selected_category, @Param("isCorrect") Boolean isCorrect);
}
