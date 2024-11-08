package com.cdpo_spring_developer.service.mapper;

import com.cdpo_spring_developer.service.dto.ServiceRequestDTO;
import com.cdpo_spring_developer.service.dto.ServiceResponseDTO;
import com.cdpo_spring_developer.service.entity.Services;
import org.springframework.stereotype.Service;

@Service
public class ServiceMapper {
    public Services mapToEntity(ServiceRequestDTO serviceRequest){
        return Services.builder()
                .price(serviceRequest.price())
                .build();
    }
    public ServiceResponseDTO mapToDTO(Services service) {
        return new ServiceResponseDTO(
                service.getServiceCategory().getServiceName(),
                service.getPrice()
                );
    }
}
