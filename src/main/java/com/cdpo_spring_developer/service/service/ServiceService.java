package com.cdpo_spring_developer.service.service;

import com.cdpo_spring_developer.service.dto.ServiceRequestDTO;
import com.cdpo_spring_developer.service.dto.ServiceResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ServiceService {
    public Long createService(@Valid @RequestBody ServiceRequestDTO serviceRequest){

        return 0L;
    }

    public ServiceRequestDTO editService(@Valid @RequestBody ServiceRequestDTO serviceRequest){
        return null;
    }

    public List<ServiceResponseDTO> getServiceList() {
        return null;
    }

    public ServiceResponseDTO getServiceById(@Positive @RequestParam int id){
        return null;
    }

}
