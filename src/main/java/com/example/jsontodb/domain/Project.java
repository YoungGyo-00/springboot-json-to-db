package com.example.jsontodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    private String classId;

    private String className;

    private String superCategory;

    @Column(length = 20)
    private String annotationType;

    private String propertyName;

    @Column(length = 5)
    private String propertyUnit;
}
