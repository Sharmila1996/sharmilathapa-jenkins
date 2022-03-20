package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {

	List<Booking> findByVendorId(Long vendorId);

}
