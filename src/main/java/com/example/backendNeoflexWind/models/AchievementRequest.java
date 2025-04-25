package com.example.backendNeoflexWind.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchievementRequest {
    private Long userId;
    private Long achievementId;
}