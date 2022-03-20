package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Vehicles;

public interface VehicleRepository extends CrudRepository<Vehicles, Long> {

	public Vehicles findByRegNo(String regNo);

	public List<Vehicles> findByName(String name);


}
