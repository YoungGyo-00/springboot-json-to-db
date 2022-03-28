package com.example.jsontodb.repository;

import com.example.jsontodb.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
