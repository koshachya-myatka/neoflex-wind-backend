package com.example.backendNeoflexWind.services;

import com.example.backendNeoflexWind.models.*;
import com.example.backendNeoflexWind.repositories.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DataService {
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;
    private final ShopItemRepository shopItemRepository;
    private final EducationItemRepository educationItemRepository;
    private final TimeMachineQuestionRepository timeMachineQuestionRepository;
    private final ObjectMapper objectMapper;

    public boolean addTestUser() {
        if (userRepository.findByUsername("test").isPresent()) {
            return true;
        }
        userRepository.save(User.builder().username("test").email("test@gmail.com").password("123456").points(2000).build());
        return false;
    }

    @SneakyThrows
    public void addAchievements() {
        try (InputStream input = getClass().getResourceAsStream("/data/achievements.json")) {
            List<Achievement> achievements = objectMapper.readValue(input, new TypeReference<List<Achievement>>() {
            });
            achievementRepository.saveAll(achievements);
        }
    }

    @SneakyThrows
    public void addShopItems() {
        try (InputStream input = getClass().getResourceAsStream("/data/shop_items.json")) {
            List<ShopItem> shopItems = objectMapper.readValue(input, new TypeReference<List<ShopItem>>() {
            });
            shopItemRepository.saveAll(shopItems);
        }
    }

    @SneakyThrows
    public void addEducationItems() {
        try (InputStream input = getClass().getResourceAsStream("/data/education_items.json")) {
            List<EducationItem> educationItems = objectMapper.readValue(input, new TypeReference<List<EducationItem>>() {
            });
            educationItemRepository.saveAll(educationItems);
        }
    }

    public void addTimeMachineQuestions() {
        Stream.of(
                "/data/time_machine_questions_birth_of_code.json",
                "/data/time_machine_questions_breakthrough_era.json",
                "/data/time_machine_questions_digital_revolution.json"
        ).forEach(this::addQuestionsForEra);
    }

    @SneakyThrows
    private void addQuestionsForEra(String filePath) {
        try (InputStream input = getClass().getResourceAsStream(filePath)) {
            List<TimeMachineQuestion> timeMachineQuestions = objectMapper.readValue(input, new TypeReference<List<TimeMachineQuestion>>() {
            });
            timeMachineQuestionRepository.saveAll(timeMachineQuestions);
        }
    }
}
