package com.Jb.FinalCouponProject.controller.advise;

import com.Jb.FinalCouponProject.controller.CompanyController;
import com.Jb.FinalCouponProject.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackageClasses = CompanyController.class)
@ResponseBody
public class ControllerAdvise {


    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleCompanyNotFoundException(CompanyNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CouponExistsEcxeption.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleCouponExistsException(CouponExistsEcxeption ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.IM_USED, ex.getMessage());
    }

    @ExceptionHandler(CustomerDoesNotExist.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleCustomerDoesExist(CustomerDoesNotExist ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler(InvalidAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleInvalidAmountException(InvalidAmountException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidCompanyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleInvalidCompanyException(InvalidCompanyException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidCouponExceptions.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleInvalidCouponException(InvalidCouponExceptions ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UuidAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public ProblemDetail handleUuidAlreadyExistsException(UuidAlreadyExistsException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.IM_USED, ex.getMessage());
    }

}
