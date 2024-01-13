package com.restaurantproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantproject.entities.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository< Reservation ,Long>
{

	List<Reservation> findAllByUserId(Long customerId);

}
