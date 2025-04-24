package com.example.backendNeoflexWind.controllers;

import com.example.backendNeoflexWind.dto.EducationAnswerDto;
import com.example.backendNeoflexWind.models.EducationAttempt;
import com.example.backendNeoflexWind.models.EducationItem;
import com.example.backendNeoflexWind.services.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;

    @GetMapping("/items")
    public ResponseEntity<List<EducationItem>> getAllEducationItems() {
        List<EducationItem> items = educationService.findAllEducationItems();
        if (items.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/attempts/{userId}")
    public List<EducationAttempt> getEducationAttemptsByUserId(@PathVariable Long userId) {
        return educationService.findUserEducationAttempts(userId);
    }

    @PostMapping("/attempts/increment/{userId}")
    public ResponseEntity<Void> incrementAttempts(@PathVariable Long userId) {
        if (!educationService.incrementAttempts(userId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/answers/")
    public ResponseEntity<Void> saveUserAnswer(@RequestBody EducationAnswerDto dto) {
        if (!educationService.saveAnswers(dto.getUserId(), dto.getItemId(), dto.getSelectedCategory(), dto.getIsCorrect())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
