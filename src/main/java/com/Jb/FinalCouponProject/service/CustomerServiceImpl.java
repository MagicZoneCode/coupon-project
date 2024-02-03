package com.Jb.FinalCouponProject.service;

import com.Jb.FinalCouponProject.data.dto.CouponDto;
import com.Jb.FinalCouponProject.data.entity.CouponEntity;
import com.Jb.FinalCouponProject.data.entity.CustomerEntity;
import com.Jb.FinalCouponProject.data.repository.CompanyRepository;
import com.Jb.FinalCouponProject.data.repository.CouponRepository;
import com.Jb.FinalCouponProject.data.repository.CustomerRepository;
import com.Jb.FinalCouponProject.exceptions.*;
import com.Jb.FinalCouponProject.mapper.Mapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Builder

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;
    private final Mapper mapper;

    @Override

    public List<CouponDto> getAllByCouponsWithCloseDateToExpire(UUID uuid) {
        CustomerEntity customer
                = customerRepository.findByUuid(uuid)
                .orElseThrow((() -> new ClientNotFoundException("There is no such a client")));
        List<CouponEntity> coupons = customer.getCoupons();
        couponRepository.getAllPurchasedCouponsBySevenDate(customer.getId());
        return mapper.map(coupons);
    }


    @Override
    public List<CouponDto> findAllCoupons(UUID customerUuid) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUuid(customerUuid);
        if (customerEntityOptional.isEmpty()) {
            throw new ClientNotFoundException("There is no such a client");
        }
        List<CouponEntity> coupons = customerEntityOptional.get().getCoupons();
        return mapper.map(coupons);
    }

    @Override
    public void buyCoupon(UUID customerUuid, UUID couponUuid) throws CustomerDoesNotExist {
        Optional<CouponEntity> optionalCoupon = couponRepository.findByUuid(couponUuid);
        if (optionalCoupon.isEmpty()) {
            throw new CouponExistsEcxeption("Coupon does not exists");
        }
        Optional<CustomerEntity> optionalCustomer = customerRepository.findByUuid(customerUuid);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerDoesNotExist("There is no such is customer");
        }
        CouponEntity couponEntity = optionalCoupon.get();
        CustomerEntity customerEntity = optionalCustomer.get();

        if (customerEntity.getCoupons().contains(couponEntity)) {
            throw new CouponExistsEcxeption("You have already got this coupon");
        }
        if (couponEntity.getAmount() <= 0) {
            throw new InvalidAmountException("The is no availability for requested coupon ");
        }
        if (couponEntity.getEndDate().isBefore(LocalDate.now())) {
            throw new InvalidCouponExceptions("The end date of coupon is expired");


        }

        customerEntity.getCoupons().add(couponEntity);
        customerRepository.save(customerEntity);

    }

    @Override
    public List<CouponDto> notPurchasedCoupons(UUID customerUuid) {
        Optional<CustomerEntity> byUuid = customerRepository.findByUuid(customerUuid);
        if (byUuid.isEmpty()) {
            throw new CustomerDoesNotExist("There is no such a customer");
        }
        List<CouponEntity> customerCoupons = byUuid.get().getCoupons();
        List<CouponEntity> couponsNotPurchased =
                couponRepository.findAll().stream().
                        filter(coupon -> !customerCoupons.contains(coupon)).
                        collect(Collectors.toList());
        return mapper.map(couponsNotPurchased);
    }


    //   @Override
    // public List<CouponDto> getAllUnPurchased(CustomerEntity customerEntity) {
    //   CustomerEntity customer
    //         = customerRepository.findById(customerEntity.getId())
    //       .orElseThrow((() -> new ClientNotFoundException("There is no such a client")));
    //List<CouponEntity> coupons = customer.getCoupons();
    //return mapper.map(coupons);
}




