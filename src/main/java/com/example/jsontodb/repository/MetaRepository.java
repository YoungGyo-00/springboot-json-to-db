package com.example.jsontodb.repository;

import com.example.jsontodb.domain.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaRepository extends JpaRepository<Meta, String> {
}
