package com.cdpo_spring_developer.service.controller;

import com.cdpo_spring_developer.service.dto.*;
import com.cdpo_spring_developer.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("api/v1/service")
@RestController
public class UserController {
    private final UserService userService;

}
