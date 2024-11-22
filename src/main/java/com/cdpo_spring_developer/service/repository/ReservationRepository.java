package com.cdpo_spring_developer.service.repository;

import com.cdpo_spring_developer.service.constants.ServiceType;
import com.cdpo_spring_developer.service.constants.StatusType;
import com.cdpo_spring_developer.service.entity.Reservation;
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


    @Query(nativeQuery = true,
    value = "UPDATE reservation" +
            "SET order = :order," +
            "status = :status," +
            "user_id = :client_id," +
            "reservation_date = date" +
            "WHERE  id = :id")
    Reservation updateReservation(long id, List<ServiceType> order, StatusType status, long client_id, String date);


    @Query(nativeQuery = true,
    value = "UPDATE reservation" +
            "SET discount = :discount," +
            "total_price = :newTotalPrice" +
            "WHERE id = :id")
    Reservation updateDiscount(long id, double discount, double newTotalPrice);

    @Query(nativeQuery = true,
    value = "SELECT * FROM reservation" +
            "WHERE user_id = :id")
    List<Reservation> findAllByUserId(long id);

    @Query(nativeQuery = true,
    value = "UPDATE reservation" +
            "SET status = :status" +
            "WHERE id = :id")
    void updateStatus(long id, StatusType status);
}

