package com.example.backendNeoflexWind.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class EducationAnswerDto {
    private Long userId;
    private Long itemId;
    private String selectedCategory;
    private Boolean isCorrect;
}