package com.example.backendNeoflexWind.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SimpleUserDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
