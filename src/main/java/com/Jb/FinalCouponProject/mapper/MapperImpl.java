package com.Jb.FinalCouponProject.mapper;

import com.Jb.FinalCouponProject.data.dto.CompanyDto;
import com.Jb.FinalCouponProject.data.dto.CouponDto;
import com.Jb.FinalCouponProject.data.dto.CustomerDto;
import com.Jb.FinalCouponProject.data.entity.CompanyEntity;
import com.Jb.FinalCouponProject.data.entity.CouponEntity;
import com.Jb.FinalCouponProject.data.entity.CustomerEntity;
import jakarta.persistence.Column;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperImpl implements Mapper {
    @Override
    public CouponEntity map(CouponDto couponEntityDto) {
        return CouponEntity.builder().uuid(couponEntityDto.getUuid())
                .description(couponEntityDto.getDescription())
                .price(couponEntityDto.getPrice())
                .image(couponEntityDto.getImage())
                .startDate(couponEntityDto.getStartDate())
                .endDate(couponEntityDto.getEndDate())
                .build();

    }
    public List<CouponDto> map (List<CouponEntity>couponEntities){
        List<CouponDto>couponDtos=new ArrayList<>();
        for(CouponEntity couponEntity:couponEntities){
            couponDtos.add(map(couponEntity));
        }
        return couponDtos;

    }

    @Override
    public CouponDto map(CouponEntity couponEntity) {
        return CouponDto.builder()
                .uuid(couponEntity.getUuid())
                .description(couponEntity.getDescription())
                .price(couponEntity.getPrice())
                .image(couponEntity.getImage())
                .startDate(couponEntity.getStartDate())
                .endDate(couponEntity.getEndDate()).build();
    }


    @Override
    public CustomerEntity map(CustomerDto customerEntityDto) {
        return CustomerEntity.builder().uuid(customerEntityDto.getUuid())
                .firstName(customerEntityDto.getName())
                .email(customerEntityDto.getEmail()).build();

    }

    @Override
    public CustomerDto map(CustomerEntity customerEntity) {
        return CustomerDto.builder().uuid(customerEntity.getUuid())
                .name(customerEntity.getFirstName())
                .email(customerEntity.getEmail()).build();
    }

    @Override
    public CompanyEntity map(CompanyDto companyEntityDto) {
        return CompanyEntity.builder().uuid(companyEntityDto.getUuid())
                .name(companyEntityDto.getName())
                .email(companyEntityDto.getEmail()).build();

    }

    @Override
    public CompanyDto map(CompanyEntity companyEntity) {
        return CompanyDto.builder().uuid(companyEntity.getUuid())
                .name(companyEntity.getName())
                .email(companyEntity.getEmail()).build();
    }

}

