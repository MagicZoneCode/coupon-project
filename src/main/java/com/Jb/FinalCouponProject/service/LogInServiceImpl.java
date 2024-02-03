package com.Jb.FinalCouponProject.service;

import com.Jb.FinalCouponProject.data.entity.CompanyEntity;
import com.Jb.FinalCouponProject.data.entity.CouponEntity;
import com.Jb.FinalCouponProject.data.entity.CustomerEntity;
import com.Jb.FinalCouponProject.data.repository.CompanyRepository;
import com.Jb.FinalCouponProject.data.repository.CustomerRepository;
import com.Jb.FinalCouponProject.exceptions.ClientNotFoundException;
import com.Jb.FinalCouponProject.exceptions.CompanyNotFoundException;
import com.Jb.FinalCouponProject.login.ClientSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LogInServiceImpl implements LogInService {
    public static final int LENGTH_TOKEN = 10;
    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private int tokenExpirationMinutes;
    private static final int CUSTOMERTYPE = 1;
    // public  static int COMPANYTYPE=2;

    public LogInServiceImpl(CustomerRepository customerRepository, CompanyRepository companyRepository,
                            @Value("${token.expiration.minutes}") int tokenExpirationMinutes) {
        this.customerRepository = customerRepository;
        this.tokenExpirationMinutes = tokenExpirationMinutes;
        this.companyRepository = companyRepository;
    }

    @Override
    public ClientSession creatClientSession(String email, String password, int type) {//צריך להכניס משתנה לעשות ככה שגם Company וגם

        if (CUSTOMERTYPE == type) { // magic numbers
            Optional<CustomerEntity> byEmailAndPassword = customerRepository.findByEmailAndPassword(email, password);
            if (byEmailAndPassword.isEmpty()) {
                throw new ClientNotFoundException("Client not found");
            }
            CustomerEntity customerEntity = byEmailAndPassword.get();
            UUID uuid = customerEntity.getUuid();
            return ClientSession.ofNow(uuid, tokenExpirationMinutes);

        } else {
            Optional<CompanyEntity> byEmailAndPassword = companyRepository.findByEmailAndPassword(email, password);
            if (byEmailAndPassword.isEmpty()) {
                throw new CompanyNotFoundException("Client not found");
            }
            CompanyEntity companyEntity = byEmailAndPassword.get();
            UUID uuid = companyEntity.getUuid();
            return ClientSession.ofNow(uuid, tokenExpirationMinutes);
        }


    }

    @Override
    public String generateToken() {
        return UUID.randomUUID()
                .toString()
                .replaceAll("-", "")
                .substring(0, LENGTH_TOKEN);
    }
}
