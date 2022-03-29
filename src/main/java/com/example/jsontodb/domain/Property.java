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
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue
    @ToString.Exclude
    private Long id;

    private String property_name;

    private String value;

    @ManyToOne
    @JoinColumn(name = "object_id")
    @ToString.Exclude
    private Object object;
}
