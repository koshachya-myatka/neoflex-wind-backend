package com.example.backendNeoflexWind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchievementDto {
    private Long id;
    private String name;
    private String description;
    private Integer pointsReward;
    private Boolean isSecret = false;
    private String iconPath;
}
