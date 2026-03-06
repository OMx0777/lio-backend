package com.example.lio.repositories;

import com.example.lio.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    
    // This exact method name tells Spring Boot how to search the database
    List<Material> findByTitleContainingIgnoreCaseOrSubjectContainingIgnoreCase(String title, String subject);
    
}