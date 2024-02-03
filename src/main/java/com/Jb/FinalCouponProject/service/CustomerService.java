package com.Jb.FinalCouponProject.service;

import com.Jb.FinalCouponProject.data.dto.CouponDto;
import com.Jb.FinalCouponProject.data.entity.CouponEntity;
import com.Jb.FinalCouponProject.data.entity.CustomerEntity;
import com.Jb.FinalCouponProject.exceptions.CustomerDoesNotExist;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CouponDto> getAllByCouponsWithCloseDateToExpire(UUID uuid);



    List<CouponDto> findAllCoupons(UUID customerUuid);


    void buyCoupon(UUID customerUuid, UUID couponUuid) throws CustomerDoesNotExist;
    List<CouponDto>notPurchasedCoupons(UUID customerUuid);
}
