package com.artem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserPaymentMethod implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Integer accountNumber;
    private LocalDate expiryDate;

    private Boolean isDefault;
}
