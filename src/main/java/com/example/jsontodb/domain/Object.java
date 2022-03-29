package com.example.jsontodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Object {
    @Id
    @GeneratedValue
    private Long Id;

    private String class_name;

    private String annotation_type;

    @OneToMany
    @JoinColumn(name = "object_id")
    private List<Point> points = new ArrayList<>();

    private Long value;

    @ManyToOne
    @JoinColumn(name = "label_id")
    @ToString.Exclude
    private Label label;

    public void addPoint(Point point) {
        this.points.add(point);
    }
}
