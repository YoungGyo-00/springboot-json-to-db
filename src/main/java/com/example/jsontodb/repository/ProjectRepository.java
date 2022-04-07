package com.example.jsontodb.repository;

import com.example.jsontodb.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
    Project findTop1ByClassName(String class_name);
}
