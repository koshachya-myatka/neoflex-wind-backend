package com.example.backendNeoflexWind.repositories;

import com.example.backendNeoflexWind.models.EducationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EducationItemRepository extends JpaRepository<EducationItem, Long> {

}
