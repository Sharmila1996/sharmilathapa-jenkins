package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Vendor;

public interface VendorRepository extends CrudRepository<Vendor, Long> {

	public Vendor findByEmailId(String email);

	public Vendor findByEmailIdAndPassword(String email, String password);

}
