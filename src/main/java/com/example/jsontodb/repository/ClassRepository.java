package com.example.jsontodb.repository;

import com.example.jsontodb.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
}
