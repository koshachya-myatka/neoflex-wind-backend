package com.example.backendNeoflexWind.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TestAttemptDto {
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("era")
    private String era;
}
