package com.example.backendNeoflexWind.controllers;

import com.example.backendNeoflexWind.dto.AchievementDto;
import com.example.backendNeoflexWind.dto.ShopItemDto;
import com.example.backendNeoflexWind.dto.UserAchievementDto;
import com.example.backendNeoflexWind.models.AchievementRequest;
import com.example.backendNeoflexWind.models.AchievementUnlockResponse;
import com.example.backendNeoflexWind.services.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/achievement")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementService achievementService;
    @GetMapping()
    public ResponseEntity<List<AchievementDto>> getAchievements() {
        return ResponseEntity.ok(achievementService.getAchievements());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAchievementDto>> getUserAchievementsByUserId(
            @PathVariable Long userId){
        return ResponseEntity.ok(achievementService.getUserAchievements(userId));
    }
    @GetMapping("/has")
    public ResponseEntity<Map<String, Boolean>> hasAchievement(
            @RequestParam Long userId,
            @RequestParam Long achievementId) {
        boolean hasAchievement = achievementService.hasAchievement(userId, achievementId);
        return ResponseEntity.ok(Map.of("hasAchievement", hasAchievement));
    }

    @PostMapping("/unlock/")
    public ResponseEntity<AchievementUnlockResponse> unlockAchievement(
            @RequestBody AchievementRequest request) {
        AchievementUnlockResponse response = achievementService.unlockAchievement(
                request.getUserId(),
                request.getAchievementId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
