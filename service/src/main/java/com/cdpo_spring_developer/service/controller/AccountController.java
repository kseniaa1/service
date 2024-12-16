package com.cdpo_spring_developer.service.controller;

import com.cdpo_spring_developer.service.service.AccountService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("api/v1/service")
@RestController
public class AccountController {
    private final AccountService userService;

    @GetMapping("/registration")
    public String createAccount(){
        return "";
    }

    @GetMapping("/login")
    public String loginAccount(){
        return "";
    }

    @GetMapping
    public ResponseEntity<?> appointOperator(@Positive @RequestParam int id) {
        log.debug("user {} appointed to operator", id);
        return new ResponseEntity<>(userService.appointOperator(id), HttpStatus.OK);
    }

}
