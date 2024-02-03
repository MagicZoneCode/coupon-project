package com.Jb.FinalCouponProject.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "customers")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JdbcType(CharJdbcType.class)
    @Column(unique = true, updatable = false)
    private UUID uuid;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String last_name;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customers_coupons", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private List<CouponEntity> coupons;
}


