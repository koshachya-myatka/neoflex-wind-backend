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
    @Column(name = "points_reward", nullable = false)
    private Integer pointsReward;
    @Column(name = "is_secret", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isSecret = false;
    @Column(name = "icon_path", length = 100)
    private String iconPath;
}
