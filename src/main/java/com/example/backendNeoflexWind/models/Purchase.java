package com.example.backendNeoflexWind.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.security.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "item_id")
    private Long itemId;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "purchased_at", updatable = false)
    private Date purchasedAt;
    @Column(length = 20)
    private String status = "pending";
}
