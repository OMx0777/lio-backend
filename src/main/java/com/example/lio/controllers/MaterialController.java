package com.example.lio.controllers;

import com.example.lio.models.Material;
import com.example.lio.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMaterial(
            @RequestParam("title") String title,
            @RequestParam("subject") String subject,
            @RequestParam("professorName") String professorName,
            @RequestParam("fileUrl") String fileUrl) { 

        try {
            Material material = new Material();
            material.setTitle(title);
            material.setSubject(subject);
            material.setProfessorName(professorName);
            material.setFileName(fileUrl); 
            
            materialRepository.save(material);

            return ResponseEntity.ok("Secured in the Cloud Archive!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Database Error: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Material>> searchMaterials(@RequestParam(required = false, defaultValue = "") String query) {
        if (query.isEmpty()) {
            return ResponseEntity.ok(materialRepository.findAll());
        }
        return ResponseEntity.ok(materialRepository.findByTitleContainingIgnoreCaseOrSubjectContainingIgnoreCase(query, query));
    }
}