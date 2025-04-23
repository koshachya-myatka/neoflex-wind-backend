package com.example.backendNeoflexWind.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "time_machine_questions")
public class TimeMachineQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String era;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String questionText;
    @Column(nullable = false, length = 255)
    private String correctAnswer;
    @Column(length = 255)
    private String option1;
    @Column(length = 255)
    private String option2;
    @Column(length = 255)
    private String option3;
    @Column(nullable = false)
    private Integer points;
}
