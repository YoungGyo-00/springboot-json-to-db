package com.example.jsontodb.repository;

import com.example.jsontodb.domain.Object;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<Object, Long> {
}
