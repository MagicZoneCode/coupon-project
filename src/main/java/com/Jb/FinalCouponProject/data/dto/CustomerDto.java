package com.Jb.FinalCouponProject.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class CustomerDto {
    private UUID uuid;
    private String name;
    private String email;

}
