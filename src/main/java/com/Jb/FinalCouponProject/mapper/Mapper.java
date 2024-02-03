package com.Jb.FinalCouponProject.mapper;

import com.Jb.FinalCouponProject.data.dto.CompanyDto;
import com.Jb.FinalCouponProject.data.dto.CouponDto;
import com.Jb.FinalCouponProject.data.dto.CustomerDto;
import com.Jb.FinalCouponProject.data.entity.CompanyEntity;
import com.Jb.FinalCouponProject.data.entity.CouponEntity;
import com.Jb.FinalCouponProject.data.entity.CustomerEntity;

import java.util.List;

public interface Mapper {
    CouponEntity map(CouponDto couponEntityDto);
    CouponDto map(CouponEntity couponEntity);

    CustomerEntity map(CustomerDto customerEntityDto);
    CustomerDto map(CustomerEntity customerEntity);

    CompanyEntity map(CompanyDto companyEntityDto);
   CompanyDto map(CompanyEntity companyEntity);
   List<CouponDto> map (List<CouponEntity>companyEntities);

}
