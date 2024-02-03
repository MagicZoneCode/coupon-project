package com.Jb.FinalCouponProject.exceptions;

public class CustomerDoesNotExist extends RuntimeException {
    public CustomerDoesNotExist(String message) {
        super(message);
    }
}
