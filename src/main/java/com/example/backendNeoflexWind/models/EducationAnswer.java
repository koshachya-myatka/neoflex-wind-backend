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
@Table(name = "education_answers")
public class EducationAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private EducationItem item;
    @Column(name = "selected_category", nullable = false, length = 20)
    private String selectedCategory;
    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;
    @Column(name = "answered_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime answeredAt;
}
