package com.example.backendNeoflexWind.services;

import com.example.backendNeoflexWind.dto.EducationAnswerDto;
import com.example.backendNeoflexWind.models.*;
import com.example.backendNeoflexWind.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EducationService {
    private final EducationAttemptRepository educationAttemptRepository;
    private final EducationItemRepository educationItemRepository;
    private final UserRepository userRepository;
    private final EducationAnswerRepository answerRepository;

    public List<EducationItem> findAllEducationItems() {
        return educationItemRepository.findAll();
    }

    public List<EducationAttempt> findUserEducationAttempts(Long userId) {
        return educationAttemptRepository.findByUserId(userId);
    }

    @Transactional
    public boolean incrementAttempts(Long userId) {
        try {
            int currentAttemptsUsed = educationAttemptRepository.countByUserId(userId);
            educationAttemptRepository.incrementAttempt(userId, currentAttemptsUsed + 1);
        } catch (Exception e) {
//            throw e;
            return false;
        }
        return true;
    }

    @Transactional
    public boolean saveAnswers(Long userId, Long itemId, String selected_category, Boolean isCorrect) {
        try {
            answerRepository.saveUserAnswer(userId, itemId, selected_category, isCorrect);
        } catch (Exception e) {
//            throw e;
            return false;
        }
        return true;
    }
}
