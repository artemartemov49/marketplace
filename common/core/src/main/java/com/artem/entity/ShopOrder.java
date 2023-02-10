package com.artem.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@EqualsAndHashCode(exclude = {"orderLines"})
@ToString(exclude = {"orderLines"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ShopOrder implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private UserPaymentMethod paymentMethod;

    @ManyToOne
    private ShippingMethod shippingMethod;

    @OneToMany
    @Builder.Default
    private List<OrderLine> orderLines = new ArrayList<>();

    private Integer orderTotal;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
