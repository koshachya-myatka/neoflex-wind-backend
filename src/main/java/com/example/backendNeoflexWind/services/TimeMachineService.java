package com.example.backendNeoflexWind.services;

import com.example.backendNeoflexWind.models.TestAttempt;
import com.example.backendNeoflexWind.models.TimeMachineQuestion;
import com.example.backendNeoflexWind.models.UserAnswer;
import com.example.backendNeoflexWind.repositories.TestAttemptRepository;
import com.example.backendNeoflexWind.repositories.TimeMachineQuestionRepository;
import com.example.backendNeoflexWind.repositories.UserAnswerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeMachineService {
    private final TestAttemptRepository testAttemptRepository;
    private final TimeMachineQuestionRepository timeMachineQuestionRepository;
    private final UserAnswerRepository userAnswerRepository;

    public List<TestAttempt> findUsersTestAttempts(Long userId) {
        return testAttemptRepository.findByUserId(userId);
    }

    public List<TimeMachineQuestion> findTimeMachineQuestionsByEra(String era) {
        return timeMachineQuestionRepository.findByEra(era);
    }

    @Transactional
    public void incrementUsersTestAttempts(Long userId, String era) {
        testAttemptRepository.incrementAttempt(userId, era);
    }

    @Transactional
    public void submitAnswer(UserAnswer userAnswer) {
        userAnswerRepository.saveUserAnswer(userAnswer.getUser().getId(), userAnswer.getQuestion().getId(), userAnswer.getAnswer(), userAnswer.getIsCorrect());
    }

}
