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

    private String Image_info;

    private String Label_id;

    @OneToMany(mappedBy = "labelPath")
    @ToString.Exclude
    private List<LabelPath> labelPaths = new ArrayList<>();

}
