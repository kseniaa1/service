package com.cdpo_spring_developer.service.service;

import com.cdpo_spring_developer.service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public Long appointOperator(int id){
        accountRepository.appointOperator(id);
        return 0L;
    }
}
