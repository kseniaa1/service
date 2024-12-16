package com.cdpo_spring_developer.service.mapper;

import com.cdpo_spring_developer.service.dto.ServiceRequestDTO;
import com.cdpo_spring_developer.service.dto.ServiceResponseDTO;
import com.cdpo_spring_developer.service.entity.Services;
import org.springframework.stereotype.Service;

@Service
public class ServiceMapper {
    public Services mapToEntity(ServiceRequestDTO serviceRequest){
        return Services.builder()
                .serviceType(serviceRequest.name())
                .price(serviceRequest.price())
                .duration(serviceRequest.duration())
                .build();
    }
    public ServiceResponseDTO mapToDTO(Services service) {
        return new ServiceResponseDTO(
                service.getServiceType(),
                service.getPrice(),
                service.getDuration()
                );
    }
}
