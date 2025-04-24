package com.example.backendNeoflexWind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerBatchRequest {
    private Long userId;
    private List<EducationAnswerDto> answers;
    private Integer pointsEarned;
}