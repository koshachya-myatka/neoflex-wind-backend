package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.UserAnswer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    @Modifying
    @Transactional
    @Query("INSERT INTO user_answers (user_id, question_id, answer, is_сorrect) " +
            "VALUES (:userId, :questionId, :answer, :isCorrect) " +
            "ON CONFLICT (user_id, question_id) DO UPDATE SET answer = :answer, is_сorrect = :isCorrect")
    void saveUserAnswer(@Param("userId") Long userId, @Param("questionId") Long questionId,
                        @Param("answer") String answer, @Param("isCorrect") Boolean isCorrect);
}
