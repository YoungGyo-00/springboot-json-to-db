package com.example.jsontodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Label {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "label")
    private List<Object> objects = new ArrayList<>();

    @OneToOne
    private Category category;
}
