package com.example.backendNeoflexWind.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserPointsDto {
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("points")
    private Integer points;
}
