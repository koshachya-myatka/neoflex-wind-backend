package com.example.backendNeoflexWind.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "education_items")
public class EducationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(name = "short_description", nullable = false, columnDefinition = "TEXT")
    private String shortDescription;
    @Column(name = "full_description", nullable = false, columnDefinition = "TEXT")
    private String fullDescription;
    @Column(name = "correct_category", nullable = false, length = 20)
    private String correctCategory;
    @Column(nullable = false)
    private Integer points;
}
