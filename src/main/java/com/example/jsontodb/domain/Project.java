package com.example.jsontodb.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "project_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseTimeEntity{
    @Id
    private String classId;

    private String superCategory;

    @Column(length = 20)
    private String annotationType;

    private String propertyName;

    @Column(length = 5)
    private String propertyUnit;

    @ManyToOne
    @JoinColumn(name = "class_number")
    private Class classNumber;
}
