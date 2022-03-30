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
    @GeneratedValue
    private Long id;

    private String Data_key;

    private String Label_id;

    private String label_path;

    @OneToOne
    private ImageInfo imageInfo;

}
