package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET points = points + :points WHERE id = :userId", nativeQuery = true)
    void updateUserPoints(@Param("userId") Long userId, @Param("points") Integer points);
}
