package com.Jb.FinalCouponProject.service;

import com.Jb.FinalCouponProject.login.ClientSession;

public interface LogInService {
    ClientSession creatClientSession(String email, String password,int type );

    String generateToken();
}
