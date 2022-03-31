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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String className;

    private String annotationType;

    @OneToMany
    @JoinColumn(name = "object_id")
    private List<Point> points = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "object_id")
    private List<Property> properties = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "label_id")
    @ToString.Exclude
    private Label label;

    public void addPoint(Point point) {
        this.points.add(point);
    }
    public void addProperty(Property property) {
        this.properties.add(property);
    }
}
