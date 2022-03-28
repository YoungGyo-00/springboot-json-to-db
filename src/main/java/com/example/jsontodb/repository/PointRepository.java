package com.example.jsontodb.repository;

import com.example.jsontodb.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
