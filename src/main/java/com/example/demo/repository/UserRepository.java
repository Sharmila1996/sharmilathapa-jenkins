package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Users;

public interface UserRepository extends CrudRepository<Users,Long>{

	public Users findByEmailId(String email);

	public Users findByEmailIdAndPassword(String email, String password);

}
