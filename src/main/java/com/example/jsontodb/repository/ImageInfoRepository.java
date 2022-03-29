package com.example.jsontodb.repository;

import com.example.jsontodb.domain.ImageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageInfoRepository extends JpaRepository<ImageInfo, Long> {
}
