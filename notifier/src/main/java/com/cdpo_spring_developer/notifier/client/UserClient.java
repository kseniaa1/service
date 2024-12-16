package com.cdpo_spring_developer.notifier.client;

import java.util.List;

import com.cdpo_spring_developer.notifier.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "userClient", url = "${user.service.url}")
public interface UserClient {
    List<UserDto> getUsersData();
    UserDto getUserDataById(Long id);
}
