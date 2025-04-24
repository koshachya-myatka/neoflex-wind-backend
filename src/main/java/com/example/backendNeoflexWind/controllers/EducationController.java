package com.example.backendNeoflexWind.controllers;

import com.example.backendNeoflexWind.models.EducationItem;
import com.example.backendNeoflexWind.services.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;

    @GetMapping("/attempts/{userId}")
    public ResponseEntity<?> getEducationAttemptsByUserId(@PathVariable Long userId) {
        return educationService.getEducationAttempts(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/items")
    public ResponseEntity<List<EducationItem>> getAllEducationItems() {
        List<EducationItem> items = educationService.getAllEducationItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/attempts/increment/")
    public ResponseEntity<Void> incrementAttempts(@RequestBody Map<String, Long> request) {
        educationService.incrementAttempts(request.get("userId"));
        return ResponseEntity.ok().build();
    }
//    @PostMapping("/answers/")
//    public ResponseEntity<Void> saveAnswers(
//            @RequestBody AnswerBatchRequest request) {
//
//        educationService.saveAnswers(request.getAnswers());
//        educationService.addPoints(request.getUserId(), request.getPointsEarned());
//
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
}
