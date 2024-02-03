package com.Jb.FinalCouponProject.controller;

import com.Jb.FinalCouponProject.login.ClientSession;
import com.Jb.FinalCouponProject.login.LogInCred;
import com.Jb.FinalCouponProject.service.LogInService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@Controller // @RestController
//@ResponseBody
@RestController // @Controller + @ResponseBody
@RequestMapping("/login/") // http://localhost:8080/login
@RequiredArgsConstructor

public class LoginController {
    private final LogInService logInService;
    private final Map<String, ClientSession> tokens;

    //@Value("stam.violeta")
    //private int stam;

    // @PostMapping - create / body
    // @GetMapping - read (get all coupons)
    // @PutMapping - update row
    // @PatchMapping - update cell
    // @DeleteMapping

    // @RequestBody - get object {}
    // @RequestParam - everything else (not id) - getCouponsByMaxPrice(double price)
    // @PathVariable - id (pk) - getCompanyById(int id)

    @PostMapping // http://localhost:8080/login
    public ResponseEntity<String> logIn(@RequestBody LogInCred logInCred) {
        String email = logInCred.email();
        String password = logInCred.password();
        int type =logInCred.type();

        String token = logInService.generateToken();
        ClientSession clientSession = logInService.creatClientSession(email, password,type);
        tokens.put(token, clientSession);
        return ResponseEntity.ok(token);
    }

}

