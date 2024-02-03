package com.Jb.FinalCouponProject.controller;

import com.Jb.FinalCouponProject.data.dto.CompanyDto;
import com.Jb.FinalCouponProject.data.dto.CouponDto;
import com.Jb.FinalCouponProject.login.ClientSession;
import com.Jb.FinalCouponProject.login.SessionManager;
import com.Jb.FinalCouponProject.service.CompanyService;
import jakarta.servlet.Servlet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/api")
@RequiredArgsConstructor

public class CompanyController {
    private final CompanyService companyService;
    private final SessionManager sessionManager;


    @GetMapping("/{token}/{uuid}getAllCoupons")
    public ResponseEntity<List<CouponDto>> getAllCouponsByUuid(@PathVariable String token, @PathVariable UUID uuid) {
        return ResponseEntity.of(Optional.ofNullable(companyService.findAllCoupons(sessionManager.handleToken(token))));

    }

    @DeleteMapping("/{token}/{couponUuid}/deleteCouponById")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCouponByUUid(@PathVariable String token, @PathVariable UUID couponUuid) {
        sessionManager.handleToken(token);
        companyService.deleteCoupon(couponUuid);

    }

    @PostMapping("/{token}/createCoupon")
    public ResponseEntity<CouponDto> createCoupon(@RequestBody CouponDto couponDto, @PathVariable String token) {
        // Location: http://localhost:8080/.../some-random-for-the-new-coupon
        CouponDto createdCoupon = companyService.createCoupon(couponDto, sessionManager.handleToken(token));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                /*http://localhost:8080/api/createCoupon*/
                .path("/{id}")
                .buildAndExpand(createdCoupon.getUuid())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdCoupon);
    }

    @PostMapping("/{token}/{amount}/updateCoupon")
    public ResponseEntity<CouponDto> updateCoupon
            (@PathVariable String token, @PathVariable int amount) {
        CouponDto couponUpdated = companyService.updateCoupon(sessionManager.handleToken(token), amount);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                /*http://localhost:8080/api/updateCoupon*/
                .path("/{id}")
                .buildAndExpand(couponUpdated.getUuid())
                .toUri();
        return ResponseEntity.created(uri)
                .body(couponUpdated);
    }

}

