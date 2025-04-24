package com.example.backendNeoflexWind.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserAnswerDto {
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("questionId")
    private Long questionId;
    @JsonProperty("answer")
    private String answer;
    @JsonProperty("isCorrect")
    private Boolean isCorrect;
}
