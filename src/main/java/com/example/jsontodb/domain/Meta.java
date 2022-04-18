package com.example.jsontodb.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Meta extends BaseTimeEntity{
    @Id
    private String id;

    @Column(unique = true)
    private String labelId;

    @Column(length = 5)
    private int height;

    @Column(length = 5)
    private int width;

    private LocalDateTime labelingTime;

}
