package com.Jb.FinalCouponProject.data.repository;

import com.Jb.FinalCouponProject.data.entity.CompanyEntity;
import com.Jb.FinalCouponProject.data.entity.CouponEntity;
import com.Jb.FinalCouponProject.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Integer> {
    Optional<CompanyEntity> findByEmailAndPassword(String email, String password);
    Optional<CompanyEntity>findByUuid(UUID uuid);

    void deleteByUuid(UUID couponUuid);

    boolean existsByUuid(UUID uuid);


}
