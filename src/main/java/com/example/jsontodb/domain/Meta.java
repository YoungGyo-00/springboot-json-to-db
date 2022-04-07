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
public class Meta {
    @Id
    private String id;

    @Column(unique = true)
    private String labelId;

    @Column(length = 5)
    private int height;

    @Column(length = 5)
    private int width;

}
