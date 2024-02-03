package com.Jb.FinalCouponProject.data.repository;

import com.Jb.FinalCouponProject.data.entity.CouponEntity;
import com.Jb.FinalCouponProject.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<CouponEntity,Integer> {
    boolean existsByUuid(UUID uuid);
    @Query(value = "SELECT c.* FROM coupons c INNER JOIN companies co ON c.company_id = co.id WHERE co.uuid = ?", nativeQuery = true)
    List<CouponEntity> findAllByCompanyUUID(String companyUuid);
    Optional<CouponEntity> findByUuid(UUID uuid);

    @Query(value = "select * from coupons c where c.end_date<=current_date+7 AND c.end_date >= current_date", nativeQuery = true)
    List<CouponEntity> getAllCouponsByDate(CustomerEntity customerEntity);

    @Query(value = "select c.* from coupons c INNER JOIN customers_coupons cc ON c.id = cc.coupon_id where cc.customer_id = ? AND c.end_date<=current_date+7 AND c.end_date >= current_date", nativeQuery = true)
    List<CouponEntity> getAllPurchasedCouponsBySevenDate(int customer_id);
}
