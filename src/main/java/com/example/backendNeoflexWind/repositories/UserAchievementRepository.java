package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
}
