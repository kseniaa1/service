package com.cdpo_spring_developer.service.repository;

import com.cdpo_spring_developer.service.constants.ServiceType;
import com.cdpo_spring_developer.service.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Services, Long> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM services" +
            "WHERE (:type == NULL OR category == :type) AND " +
            "(:priceFrom == NULL OR price > :priceFrom) AND " +
            "(:priceTo == NULL OR price < :priceTo)"
    )
    List<Services> findByFilter(ServiceType type,
                                int priceFrom,
                                int priceTo);
    @Query(nativeQuery = true,
    value = "SELECT DISTINCT category FROM services")
    List<String> findCategories();

    @Query(nativeQuery = true,
    value = "UPDATE services SET category = :serviceType," +
            "duration = :duration," +
            "price = :price" +
            "WHERE id = :id")
    void updateServices(long id, ServiceType serviceType, double duration, double price);

    @Query(nativeQuery = true,
    value = "SELECT DISTINCT category from services" +
            "WHERE category = :serviceType")
    Services findByServiceType(ServiceType serviceType);
}
