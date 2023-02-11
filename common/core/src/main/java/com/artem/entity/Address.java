package com.artem.entity;

import java.util.ArrayList;
import java.util.List;
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

@EqualsAndHashCode(exclude = {"orderAddresses"})
@ToString(exclude = {"orderAddresses"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String unit_number;
    private String street_number;

    private String address_line1;
    private String address_line2;

    private String city;
    private String region;
    private String postalCode;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "address")
    @Builder.Default
    private List<OrderAddress> orderAddresses = new ArrayList<>();
}