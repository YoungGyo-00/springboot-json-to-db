package com.example.jsontodb.repository;

import com.example.jsontodb.domain.Object;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjectRepository extends JpaRepository<Object, String> {
    List<Object> findByMetaId(String id);
}
