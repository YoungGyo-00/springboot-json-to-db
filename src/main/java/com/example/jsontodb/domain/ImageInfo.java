package com.example.jsontodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfo {
    @Id
    @GeneratedValue
    private Long Id;

    private Long height;

    private Long width;

    @OneToOne
    @ToString.Exclude
    private Meta meta;
}
