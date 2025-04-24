package com.example.backendNeoflexWind.services;

import com.example.backendNeoflexWind.models.TestAttempt;
import com.example.backendNeoflexWind.models.TimeMachineQuestion;
import com.example.backendNeoflexWind.repositories.TestAttemptRepository;
import com.example.backendNeoflexWind.repositories.TimeMachineQuestionRepository;
import com.example.backendNeoflexWind.repositories.UserAnswerRepository;
import com.example.backendNeoflexWind.repositories.UserRepository;
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
    private final UserRepository userRepository;

    public List<TestAttempt> findUsersTestAttempts(Long userId) {
        return testAttemptRepository.findByUserId(userId);
    }

    public List<TimeMachineQuestion> findTimeMachineQuestionsByEra(String era) {
        return timeMachineQuestionRepository.findByEra(era);
    }

    @Transactional
    public boolean incrementUsersTestAttempts(Long userId, String era) {
        try {
            int currentAttemptsUsed = testAttemptRepository.countByUserIdAndEra(userId, era);
            testAttemptRepository.incrementAttempt(userId, era, currentAttemptsUsed+1);
        } catch (Exception e) {
//            throw e;
            return false;
        }
        return true;
    }

    @Transactional
    public boolean submitAnswer(Long userId, Long questionId, String answer, Boolean isCorrect) {
        try {
            userAnswerRepository.saveUserAnswer(userId, questionId, answer, isCorrect);
        } catch (Exception e) {
//            throw e;
            return false;
        }
        return true;
    }
}
