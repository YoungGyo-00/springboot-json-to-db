package com.example.jsontodb.repository;

import com.example.jsontodb.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findByClassName(String class_name);
}
