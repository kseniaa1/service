package com.cdpo_spring_developer.service.repository;

import com.cdpo_spring_developer.service.entity.Reservation;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query(nativeQuery = true,
    value = "SELECT * FROM reservation" +
            "WHERE reservation_date = :reservationDate" +
            "AND status=PLANNED")
    List<Reservation> findAllByReservationDateAndStatusPlanned(String reservationDate);

    @Query(nativeQuery = true,
    value = "SELECT reservationDate, SUM(total_price) FROM reservation" +
            "WHERE reservationDate >= :dateFrom" +
            "AND reservationDate <= :dateTo" +
            "GROUP BY reservationDate" +
            "ORDER BY reservationDate")
    Map<String, Integer> getIncomeByPeriod(String dateFrom, String dateTo);
}

