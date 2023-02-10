package com.artem.entity;

import java.util.List;
import java.util.Locale.Category;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(exclude = {"variationOptions"})
@ToString(exclude = {"variationOptions"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Variation implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    private ProductCategory category;

    @OneToMany(mappedBy = "category")
    private List<VariationOption> variationOptions;
}
