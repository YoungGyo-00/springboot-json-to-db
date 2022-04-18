package com.example.jsontodb.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Object extends BaseTimeEntity{
    @Id
    private String id;

    @Column(length = 3)
    private Integer propertyValue;

    @Column(length = 20000)
    private String points;

    @ManyToOne
    @JoinColumn(name = "meta_id")
    private Meta meta;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Project project;

}
