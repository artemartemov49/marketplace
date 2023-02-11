package com.artem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductItem implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Product product;

    private Integer quantity;
    private Integer price;

    @ManyToMany
    @JoinTable(name = "product_item_variation_option",
            joinColumns = {@JoinColumn(name = "product_item_id")},
            inverseJoinColumns = {@JoinColumn(name = "variation_option_id")}
    )
    private List<VariationOption> productItems;
}
