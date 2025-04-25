package com.example.backendNeoflexWind.controllers;

import com.example.backendNeoflexWind.dto.ShopItemDto;
import com.example.backendNeoflexWind.models.Purchase;
import com.example.backendNeoflexWind.models.ShopItem;
import com.example.backendNeoflexWind.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    @GetMapping("/items")
    public ResponseEntity<List<ShopItemDto>> getAvailableItems() {
        return ResponseEntity.ok(shopService.getAvailableItems());
    }
    @PostMapping("/purchases/")
    public ResponseEntity<Map<String, Object>> createPurchase(
            @RequestBody Map<String, Long> request) {
        Purchase purchase = shopService.createPurchase(
                request.get("userId"),
                request.get("itemId")
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "id", purchase.getId(),
                        "userId", purchase.getUserId(),
                        "itemId", purchase.getItemId(),
                        "status", purchase.getStatus(),
                        "purchasedAt", purchase.getPurchasedAt()
                )
        );
    }

    @GetMapping("/purchases/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getPurchases(
            @RequestParam Long userId) {
        List<Purchase> purchases = shopService.findByUserId(userId);
        List<Map<String, Object>> response = purchases.stream()
                .map(p -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", p.getId());
                    map.put("userId", p.getUserId());
                    map.put("itemId", p.getItemId());
                    map.put("status", p.getStatus());
                    map.put("purchasedAt", p.getPurchasedAt());
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/items/popular/{limit}")
    public ResponseEntity<List<Map<String, Object>>> getPopularItems(
            @RequestParam(defaultValue = "5") int limit) {
        List<Object[]> results = shopService.findPopularItems(limit);

        List<Map<String, Object>> response = results.stream()
                .map(result -> {
                    ShopItem item = (ShopItem) result[0];
                    Long purchaseCount = (Long) result[1];

                    Map<String, Object> map = new HashMap<>();
                    map.put("id", item.getId());
                    map.put("name", item.getName());
                    map.put("description", item.getDescription());
                    map.put("price", item.getPrice());
                    map.put("stock", item.getStock());
                    map.put("imagePath", item.getImagePath());
                    map.put("purchaseCount", purchaseCount);
                    return map;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
