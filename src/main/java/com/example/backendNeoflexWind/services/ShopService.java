package com.example.backendNeoflexWind.services;

import com.example.backendNeoflexWind.dto.ShopItemDto;
import com.example.backendNeoflexWind.models.Purchase;
import com.example.backendNeoflexWind.models.ShopItem;
import com.example.backendNeoflexWind.models.User;
import com.example.backendNeoflexWind.repositories.PurchaseRepository;
import com.example.backendNeoflexWind.repositories.ShopItemRepository;
import com.example.backendNeoflexWind.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final ShopItemRepository shopItemRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    public List<ShopItemDto> getAvailableItems() {
        return shopItemRepository.findAvailableItems().stream()
                .map(this::convertToDto)
                .toList();
    }

    private ShopItemDto convertToDto(ShopItem item) {
        return new ShopItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getStock(),
                item.getImagePath()
        );
    }
    @Transactional
    public Purchase createPurchase(Long userId, Long itemId) {
        // Проверки товара и пользователя
        ShopItem item = shopItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (item.getStock() != null && item.getStock() <= 0) {
            throw new RuntimeException("Item out of stock");
        }

        if (user.getPoints() < item.getPrice()) {
            throw new RuntimeException("Not enough points");
        }

        // Обновление данных
        if (item.getStock() != null) {
            item.setStock(item.getStock() - 1);
            shopItemRepository.save(item);
        }

        user.setPoints(user.getPoints() - item.getPrice());
        userRepository.save(user);

        // Создание покупки
        Purchase purchase = new Purchase();
        purchase.setUserId(userId);
        purchase.setItemId(itemId);
        purchase.setStatus("completed");
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> findByUserId(Long userId) {
        return purchaseRepository.findByUserId(userId);
    }
    public List<Object[]> findPopularItems(int limit) {
        return shopItemRepository.findPopularItems(limit);
    }
}
