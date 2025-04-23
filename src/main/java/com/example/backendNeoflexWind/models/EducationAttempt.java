package com.example.backendNeoflexWind.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "education_attempts")
public class EducationAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer attemptsUsed = 0;
    private LocalDateTime lastAttempt;
    @Column(columnDefinition = "INTEGER DEFAULT 3")
    private Integer maxAttempts = 3;
}
