package com.Jb.FinalCouponProject.data.repository;

import com.Jb.FinalCouponProject.data.entity.CouponEntity;
import com.Jb.FinalCouponProject.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
    Optional<CustomerEntity> findByEmailAndPassword(String email, String password);
    Optional<CustomerEntity>findByUuid(UUID uuid);




  //  void findBy(Integer customerId);

    //@Query("select c from CompanyEntity c where c.coupons!=c" )
   // List<CouponEntity>getAllUnPurchased(CustomerEntity customerEntity);

}
