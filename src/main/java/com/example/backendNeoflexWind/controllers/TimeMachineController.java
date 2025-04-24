package com.example.backendNeoflexWind.controllers;

import com.example.backendNeoflexWind.dto.TestAttemptDto;
import com.example.backendNeoflexWind.dto.UserAnswerDto;
import com.example.backendNeoflexWind.models.TestAttempt;
import com.example.backendNeoflexWind.models.TimeMachineQuestion;
import com.example.backendNeoflexWind.services.TimeMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time_machine")
@RequiredArgsConstructor
public class TimeMachineController {
    private final TimeMachineService timeMachineService;

    @GetMapping("/test_attempts/{userId}")
    public List<TestAttempt> findUsersTestAttempts(@PathVariable Long userId) {
        return timeMachineService.findUsersTestAttempts(userId);
    }

    @GetMapping("/questions/{era}")
    public List<TimeMachineQuestion> findTimeMachineQuestionsByEra(@PathVariable String era) {
        return timeMachineService.findTimeMachineQuestionsByEra(era);
    }

    @PostMapping("/test_attempts/increment")
    public ResponseEntity<Void> incrementUsersTestAttempts(@RequestBody TestAttemptDto testAttemptDto) {
        if (!timeMachineService.incrementUsersTestAttempts(testAttemptDto.getUserId(), testAttemptDto.getEra())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user_answers")
    public ResponseEntity<Void> submitUsersAnswer(@RequestBody UserAnswerDto userAnswerDto) {
        if (!timeMachineService.submitAnswer(userAnswerDto.getUserId(), userAnswerDto.getQuestionId(), userAnswerDto.getAnswer(), userAnswerDto.getIsCorrect())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
