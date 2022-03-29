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
public class LabelPath {
    @Id
    @GeneratedValue
    @ToString.Exclude
    private Long Id;

    private String label_path;

    @ManyToOne
    @JoinColumn(name = "meta_id", insertable = false, updatable = false)
    @ToString.Exclude
    private Meta meta;
}
