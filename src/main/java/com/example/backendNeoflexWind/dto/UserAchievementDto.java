package com.example.backendNeoflexWind.dto;

import com.example.backendNeoflexWind.models.Achievement;
import com.example.backendNeoflexWind.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAchievementDto {
    private Long id;
    private Long userId;
    private Long achievementId;
    private LocalDateTime earnedAt;
}
