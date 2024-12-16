package com.cdpo_spring_developer.service.controller;

import com.cdpo_spring_developer.service.dto.ServiceRequestDTO;
import com.cdpo_spring_developer.service.dto.ServiceResponseDTO;
import com.cdpo_spring_developer.service.service.ServiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/api/v1/service")
@RestController
public class ServiceController {
    private final ServiceService serviceService;

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<?> createService(@Valid @RequestBody ServiceRequestDTO serviceRequest,
                                                 HttpServletRequest request){
        URI uri= URI.create("/api/v1/service?id"+
                serviceService.createService(serviceRequest));
        return ResponseEntity.created(uri).build();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<?> editService(@RequestParam @Positive int id, @Valid @RequestBody ServiceRequestDTO serviceRequest,
                                         HttpServletRequest request) {
        return new ResponseEntity<>(serviceService.editService(id, serviceRequest), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_OPERATOR"})
    @GetMapping
    public List<ServiceResponseDTO> getServiceList() {
        return serviceService.getServiceList();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_OPERATOR"})
    @GetMapping
    public ServiceResponseDTO getServiceById(@Positive @RequestParam int id){
        return serviceService.getServiceById(id);
    }
}
