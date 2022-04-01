package com.example.jsontodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Object {
    @Id
    private String id;

    private String className;

    @Column(length = 3)
    private int propertyValue;

    @Column(length = 20000)
    private String points;

    @ManyToOne
    @JoinColumn(name = "meta_id")
    private Meta meta;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Category project;

}
