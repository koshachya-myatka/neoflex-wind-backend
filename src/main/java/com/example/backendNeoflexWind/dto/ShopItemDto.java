package com.example.backendNeoflexWind.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopItemDto {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer stock;
    private String imagePath;
}