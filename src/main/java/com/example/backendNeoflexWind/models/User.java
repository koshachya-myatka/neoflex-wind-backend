package com.example.backendNeoflexWind.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(unique = true, length = 100)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer points = 0;
}