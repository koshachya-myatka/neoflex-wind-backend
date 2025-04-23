package com.example.backendNeoflexWind.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "achievements")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Integer pointsReward;
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isSecret = false;
    @Column(length = 100)
    private String iconPath;
}
