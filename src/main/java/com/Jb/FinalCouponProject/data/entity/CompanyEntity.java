package com.Jb.FinalCouponProject.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @JdbcType(CharJdbcType.class)
    @Column(unique = true, updatable = false)
    private UUID uuid;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
    @Singular
    private List<CouponEntity> coupons;
}

