package com.Jb.FinalCouponProject.service;

import com.Jb.FinalCouponProject.data.dto.CouponDto;
import com.Jb.FinalCouponProject.data.entity.CompanyEntity;
import com.Jb.FinalCouponProject.data.entity.CouponEntity;
import com.Jb.FinalCouponProject.data.repository.CompanyRepository;
import com.Jb.FinalCouponProject.data.repository.CouponRepository;
import com.Jb.FinalCouponProject.exceptions.*;
import com.Jb.FinalCouponProject.login.SessionManager;
import com.Jb.FinalCouponProject.mapper.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final Mapper mapper;
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;


    @Override
    @Transactional
    public CouponDto createCoupon(CouponDto couponDto, UUID couponUuid) {
        CompanyEntity companyFromDb = companyRepository.findByUuid(couponUuid).orElseThrow(() -> new InvalidCompanyException("There is no such a company"));

        if (couponRepository.existsByUuid(couponUuid)) {
            throw new UuidAlreadyExistsException("Coupon UUID Already exists");
        }

        CouponEntity couponToSave = mapper.map(couponDto);
        couponToSave.setCompany(companyFromDb);

        couponRepository.save(couponToSave);
        return couponDto;
    }

    @Override
    @Transactional
    public void deleteCoupon(UUID couponId) {
        if (!couponRepository.existsByUuid(couponId)) {
            throw new InvalidCouponExceptions("The coupon does not exist");

        }
        companyRepository.deleteByUuid(couponId);

    }


    @Override
    public CouponDto updateCoupon(UUID couponUuid, int amount) {
        Optional<CouponEntity> byUuid = couponRepository.findByUuid(couponUuid);
        if (byUuid.isEmpty()) {
            throw new CouponExistsEcxeption("Coupon does not exist");
        }
        if (amount < 0) {
            throw new InvalidAmountException("The amount could not be negative");
        }
        CouponEntity coupon = byUuid.get();
        coupon.setAmount(amount);
        return mapper.map(couponRepository.save(coupon));


        // CouponEntity couponToSave = mapper.map(updateCoupon(couponUuid, amount));if (couponEntity.getAmount() <= 0) {
        //  throw new InvalidCouponExceptions("The amount could not be negative");     }
//return mapper.map(couponToSave);

    }

    @Override
    public List<CouponDto> findAllCoupons(UUID companyUuid) {
        List<CouponEntity> couponsEntities =
                couponRepository.findAllByCompanyUUID(companyUuid.toString());
        return mapper.map(couponsEntities);


    }
}




