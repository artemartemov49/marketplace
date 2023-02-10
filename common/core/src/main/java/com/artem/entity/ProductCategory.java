package com.artem.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @OneToOne(fetch = FetchType.LAZY)
    private ProductCategory category;

    @OneToMany(mappedBy = "category")
    @Builder.Default
    private List<Variation> variation = new ArrayList<>();
}
