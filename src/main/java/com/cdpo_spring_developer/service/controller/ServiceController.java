package com.cdpo_spring_developer.service.controller;

import com.cdpo_spring_developer.service.dto.ReservationRequestDTO;
import com.cdpo_spring_developer.service.dto.ServiceRequestDTO;
import com.cdpo_spring_developer.service.dto.ServiceResponseDTO;
import com.cdpo_spring_developer.service.service.ServiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<?> createService(@Valid @RequestBody ServiceRequestDTO serviceRequest,
                                                 HttpServletRequest request){
        URI uri= URI.create("/api/v1/reservations?id"+
                serviceService.createService(serviceRequest));
        return ResponseEntity.created(uri).build();
    }

    @PostMapping
    public ResponseEntity<?> editService(@Valid @RequestBody ServiceRequestDTO serviceRequest,
                                         HttpServletRequest request) {
        return new ResponseEntity<>(serviceService.editService(serviceRequest), HttpStatus.OK);
    }

    @GetMapping
    public List<ServiceResponseDTO> getServiceList() {
        return serviceService.getServiceList();
    }

    @GetMapping
    public ServiceResponseDTO getServiceById(@Positive int id){
        return serviceService.getServiceById(id);
    }
}
