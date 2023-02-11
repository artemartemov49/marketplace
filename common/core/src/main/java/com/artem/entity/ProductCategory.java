package com.artem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(exclude = {"variation"})
@ToString(exclude = {"variation"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductCategory implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private ProductCategory category;

    @OneToMany(mappedBy = "category")
    @Builder.Default
    private List<Variation> variation = new ArrayList<>();
}
