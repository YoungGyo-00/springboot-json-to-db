package com.example.jsontodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double x;

    private Double y;

    @ManyToOne
    @JoinColumn(name = "object_id")
    @ToString.Exclude
    private Object object;
}
