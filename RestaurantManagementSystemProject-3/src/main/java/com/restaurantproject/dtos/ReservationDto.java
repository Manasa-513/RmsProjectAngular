package com.restaurantproject.dtos;

import java.util.Date;

import com.restaurantproject.enums.ReservationStatus;

import lombok.Data;

@Data
public class ReservationDto 
{
	 private Long id;
	    private String tableType;
	    private String description;
	    private Date dateTime;
	    private ReservationStatus  reservationStatus ;
	    private Long customerId;
	    private String CustomerName;
}
