package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopItemRepository extends JpaRepository<ShopItem, Long> {
    @Query("SELECT i FROM ShopItem i WHERE i.stock > 0 OR i.stock IS NULL")
    List<ShopItem> findAvailableItems();
    @Query("""
        SELECT i, COUNT(p.id) as purchaseCount 
        FROM ShopItem i LEFT JOIN Purchase p ON i.id = p.itemId 
        GROUP BY i 
        ORDER BY purchaseCount DESC, i.name ASC
        LIMIT :limit""")
    List<Object[]> findPopularItems(@Param("limit") int limit);
}
