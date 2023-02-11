package com.artem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(exclude = {"variationOptions"})
@ToString(exclude = {"variationOptions"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Variation implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String value;

    @ManyToOne
    private ProductCategory category;

    @OneToMany(mappedBy = "variation")
    private List<VariationOption> variationOptions;
}
