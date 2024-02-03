package com.Jb.FinalCouponProject.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "coupons")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CouponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyEntity company;
    @Enumerated(EnumType.STRING)
    private Locale.Category category;
    private String title;
    @JdbcType(CharJdbcType.class)
    @Column(unique = true, updatable = false)
    private UUID uuid;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private int amount;
    private String description;
    private BigDecimal price;
    private String image;

}
