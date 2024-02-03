package com.Jb.FinalCouponProject.service;

import com.Jb.FinalCouponProject.data.dto.CouponDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface CompanyService {


    CouponDto createCoupon(CouponDto couponDto, UUID couponUuid);
     void deleteCoupon(UUID couponId);
    CouponDto updateCoupon(UUID couponUuid,int amount);
    List<CouponDto> findAllCoupons(UUID companyUuid);
}
