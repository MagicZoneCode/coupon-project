package com.Jb.FinalCouponProject.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
public class CouponDto {
    private UUID uuid;
    private String description;
    private BigDecimal price;
    private String image;
    private LocalDate startDate;
    private LocalDate endDate;
}
