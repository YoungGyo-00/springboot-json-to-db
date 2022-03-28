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
    @GeneratedValue
    private Long id;

    private Float x;

    private Float y;

    @ManyToOne
    @JoinColumn(name = "object_id")
    @ToString.Exclude
    private Object object;
}
