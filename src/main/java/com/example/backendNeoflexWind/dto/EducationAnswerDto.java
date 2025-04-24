package com.example.backendNeoflexWind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationAnswerDto {
    private Long userId;
    private Long itemId;
    private String selectedCategory;
    private Boolean isCorrect;
}