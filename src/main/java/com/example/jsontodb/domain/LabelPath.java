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
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "meta_id")
    @ToString.Exclude
    private LabelPath labelPath;
}
