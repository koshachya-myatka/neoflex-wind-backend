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
    @Column(name = "attempts_used", columnDefinition = "INTEGER DEFAULT 0")
    private Integer attemptsUsed = 0;
    @Column(name = "last_attempt")
    private LocalDateTime lastAttempt;
    @Column(name = "max_attempts", columnDefinition = "INTEGER DEFAULT 3")
    private Integer maxAttempts = 3;
}
