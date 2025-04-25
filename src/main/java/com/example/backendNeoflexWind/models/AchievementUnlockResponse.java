package com.example.backendNeoflexWind.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AchievementUnlockResponse {
    private boolean success;
    private int pointsReward;
    private String message;
}