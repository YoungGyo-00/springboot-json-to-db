package com.example.jsontodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String fileName;

    @OneToMany
    @JoinColumn(name = "label_id")
    private List<Object> objects = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "label_id")
    private List<Category> categories = new ArrayList<>();

    @OneToOne
    private ImageInfo imageInfo;

    public void addObject(Object object) {
        this.objects.add(object);
    }
    public void addCategory(Category category) {
        this.categories.add(category);
    }
}
