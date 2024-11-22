package com.cdpo_spring_developer.service.service;

import com.cdpo_spring_developer.service.dto.ServiceRequestDTO;
import com.cdpo_spring_developer.service.dto.ServiceResponseDTO;
import com.cdpo_spring_developer.service.entity.Services;
import com.cdpo_spring_developer.service.mapper.ServiceMapper;
import com.cdpo_spring_developer.service.repository.ServiceRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServiceService {
    private final ServiceMapper serviceMapper;
    private final ServiceRepository serviceRepository;
    public ServiceResponseDTO createService(@Valid @RequestBody ServiceRequestDTO serviceRequest){
        Services service = serviceMapper.mapToEntity(serviceRequest);
        return serviceMapper.mapToDTO(serviceRepository.save(service));
    }

    public ServiceResponseDTO editService(long id, @Valid @RequestBody ServiceRequestDTO serviceRequest){
        Services service = serviceRepository.findById(id).orElseThrow(() -> new ServiceException("Записи не найдены"));
        service.setDuration(serviceRequest.duration());
        service.setServiceType(serviceRequest.name());
        service.setPrice(serviceRequest.price());
        serviceRepository.updateServices(service.getId(),service.getServiceType(), service.getDuration(), service.getPrice());
        return serviceMapper.mapToDTO(service);
    }

    public List<ServiceResponseDTO> getServiceList() {
        return serviceRepository.findAll().stream().map(serviceMapper::mapToDTO).toList();
    }

    public ServiceResponseDTO getServiceById(@Positive @RequestParam int id){
        Optional<Services> optionalService = serviceRepository.findById((long) id);
        if (optionalService.isEmpty()) throw new ServiceException("Записи не найдены");
        return serviceMapper.mapToDTO(optionalService.get());
    }
}
