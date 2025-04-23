package com.example.backendNeoflexWind.controllers;

import com.example.backendNeoflexWind.services.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;

    @PostMapping
    public ResponseEntity<Void> initializeData() {
        if (dataService.addTestUser()) {
            return ResponseEntity.ok().build();
        }
        try {
            dataService.addAchievements();
            dataService.addShopItems();
            dataService.addEducationItems();
            dataService.addTimeMachineQuestions();
        } catch (Exception e) {
//            throw e;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
}
