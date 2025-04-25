package com.example.backendNeoflexWind.services;

import com.example.backendNeoflexWind.dto.AchievementDto;
import com.example.backendNeoflexWind.dto.ShopItemDto;
import com.example.backendNeoflexWind.dto.UserAchievementDto;
import com.example.backendNeoflexWind.models.Achievement;
import com.example.backendNeoflexWind.models.AchievementUnlockResponse;
import com.example.backendNeoflexWind.models.ShopItem;
import com.example.backendNeoflexWind.models.UserAchievement;
import com.example.backendNeoflexWind.repositories.AchievementRepository;
import com.example.backendNeoflexWind.repositories.UserAchievementRepository;
import com.example.backendNeoflexWind.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AchievementService {
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;
    private final UserAchievementRepository userAchievementRepository;
    public List<AchievementDto> getAchievements() {
        return achievementRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }
    private AchievementDto convertToDto(Achievement achievement) {
        return new AchievementDto(
                achievement.getId(),
                achievement.getName(),
                achievement.getDescription(),
                achievement.getPointsReward(),
                achievement.getIsSecret(),
                achievement.getIconPath()
        );
    }
    public List<UserAchievementDto> getUserAchievements(Long userId){
        return userAchievementRepository.findByUser(userRepository.findById(userId).get()).stream()
                .map(this::convertToDtoUserAchievements)
                .toList();
    }
    private UserAchievementDto convertToDtoUserAchievements(UserAchievement achievement) {
        return new UserAchievementDto(
                achievement.getId(),
                achievement.getUser().getId(),
                achievement.getAchievement().getId(),
                achievement.getEarnedAt()
        );
    }
    public boolean hasAchievement(Long userId, Long achievementId) {
        return userAchievementRepository.existsByUserIdAndAchievementId(userId, achievementId);
    }

    @Transactional
    public AchievementUnlockResponse unlockAchievement(Long userId, Long achievementId) {
        if (userAchievementRepository.existsByUserIdAndAchievementId(userId, achievementId)) {
            throw new IllegalStateException("Achievement already unlocked");
        }

        Achievement achievement = achievementRepository.findById(achievementId)
                .orElseThrow(() -> new EntityNotFoundException("Achievement not found"));

        UserAchievement userAchievement = new UserAchievement();
        userAchievement.setUser(userRepository.findById(userId).get());
        userAchievement.setAchievement(achievement);
        userAchievement.setEarnedAt(LocalDateTime.now());
        userAchievementRepository.save(userAchievement);

        return new AchievementUnlockResponse(
                true,
                achievement.getPointsReward(),
                "Achievement unlocked!"
        );
    }
}
