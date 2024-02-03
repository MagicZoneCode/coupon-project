package com.Jb.FinalCouponProject.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class CompanyDto {
    private UUID uuid;
    private String name;
    private String email;
}
