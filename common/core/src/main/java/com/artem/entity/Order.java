package com.artem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(exclude = {"orderLine", "orderAddresses"})
@ToString(exclude = {"orderLine", "orderAddresses"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private UserPaymentMethod paymentMethod;

    @ManyToOne
    private ShippingMethod shippingMethod;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private List<OrderAddress> orderAddresses = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private List<OrderLine> orderLine = new ArrayList<>();

    private Integer orderTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;
}
