package com.example.backendNeoflexWind.services;

import com.example.backendNeoflexWind.dto.EducationAnswerDto;
import com.example.backendNeoflexWind.dto.UserAnswerDto;
import com.example.backendNeoflexWind.models.*;
import com.example.backendNeoflexWind.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EducationService {
    private final EducationAttemptRepository educationAttemptRepository;
    private final EducationAnswerRepository educationAnswerRepository;
    private final EducationItemRepository educationItemRepository;
    private final UserRepository userRepository;
    private final EducationAnswerRepository answerRepository;
    public Optional<Map<String, Integer>> getEducationAttempts(Long userId) {
        return educationAttemptRepository.findByUserId(userId)
                .map(attempts -> Map.of(
                        "attemptsUsed", attempts.getAttemptsUsed(),
                        "maxAttempts", attempts.getMaxAttempts()
                ));
    }
    public List<EducationItem> getAllEducationItems() {
        return educationItemRepository.findAll();
    }
    @Transactional
    public void incrementAttempts(Long userId) {
        // Находим пользователя или выбрасываем исключение
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        // Пытаемся найти существующую запись о попытках
        educationAttemptRepository.findByUser(user)
                .ifPresentOrElse(
                        attempt -> {
                            // Если запись существует - увеличиваем счётчик
                            attempt.setAttemptsUsed(attempt.getAttemptsUsed() + 1);
                            attempt.setLastAttempt(LocalDateTime.now());
                        },
                        () -> {
                            // Если записи нет - создаём новую
                            EducationAttempt newAttempt = new EducationAttempt();
                            newAttempt.setUser(user);
                            newAttempt.setAttemptsUsed(1);
                            newAttempt.setLastAttempt(LocalDateTime.now());
                            newAttempt.setMaxAttempts(3); // Значение по умолчанию
                            educationAttemptRepository.save(newAttempt);
                        }
                );
    }
//    @Transactional
//    public void saveAnswers(List<EducationAnswerDto> answers) {
//        answers.forEach(dto -> {
//            EducationAnswer answer = answerRepository
//                    .findByUserIdAndItemId(dto.getUserId(), dto.getItemId())
//                    .orElseGet(EducationAnswer::new);
//
//            answer.setUser(userRepository.findById(dto.getUserId())
//                    .orElseThrow(() -> new EntityNotFoundException("User not found")));
//            answer.setItem(educationItemRepository.findById(dto.getItemId())
//                    .orElseThrow(() -> new EntityNotFoundException("Item not found")));
//            answer.setSelectedCategory(dto.getSelectedCategory());
//            answer.setIsCorrect(dto.getIsCorrect());
//            answer.setAnsweredAt(LocalDateTime.now());
//
//            answerRepository.save(answer);
//        });
//    }
//    @Transactional
//    public void addPoints(Long userId, Integer points) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//        user.setPoints(user.getPoints() + points);
//    }
}
