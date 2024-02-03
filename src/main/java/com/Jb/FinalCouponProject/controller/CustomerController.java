package com.Jb.FinalCouponProject.controller;

import com.Jb.FinalCouponProject.data.dto.CouponDto;
import com.Jb.FinalCouponProject.login.SessionManager;
import com.Jb.FinalCouponProject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final SessionManager sessionManager;

    @GetMapping("/{token}/{uuid}/closeToExpire")
    public ResponseEntity<List<CouponDto>> getCloseExpiredDateCoupons
            (@PathVariable String token, @PathVariable UUID uuid) {
        return ResponseEntity.ok(customerService.getAllByCouponsWithCloseDateToExpire
                (sessionManager.handleToken(token)));

    }

    @GetMapping("/{token}/{customerUuid}/findCouponsByUuid")
    public ResponseEntity<List<CouponDto>> findByCustomerId
            (@PathVariable String token, @PathVariable UUID customerUuid) {
        return ResponseEntity.ok(customerService.findAllCoupons
                (sessionManager.handleToken(token)));

    }

    @GetMapping("/{token}/{clientUuid}/findUnPurchasedCouponsById")
    public ResponseEntity<List<CouponDto>>
    notPurchasedCouponsByClientId(@PathVariable String token, @PathVariable UUID clientUuid) {
        return ResponseEntity.ok(customerService.notPurchasedCoupons(
                sessionManager.handleToken(token)));
    }

}
