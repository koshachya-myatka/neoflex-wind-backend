package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.Achievement;
import com.example.backendNeoflexWind.models.User;
import com.example.backendNeoflexWind.models.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
    List<UserAchievement> findByUser(User user);
    Boolean existsByUserIdAndAchievementId(Long userId, Long achievementsId);
}
