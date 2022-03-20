package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.model.Booking;
import com.example.demo.model.Users;
import com.example.demo.model.Vehicles;
import com.example.demo.model.Vendor;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.VendorRepository;

@Service
public class RentingService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private VendorRepository vendorRepo;
	@Autowired
	private VehicleRepository vehicleRepo;
	@Autowired
	private BookingRepository bookingRepo;
   public Users checkUserByEmail(String email)
   {
	   return userRepo.findByEmailId(email);
   }
   public Users saveUser(Users user)
   {
	   return userRepo.save(user);
   }
   public Admin checkAdminByEmail(String email)
   {
	   return adminRepo.findByEmailId(email);
   }
   public Admin saveAdmin(Admin admin)
   {
	   return adminRepo.save(admin);
   }
   public Vendor checkVendorByEmail(String email)
   {
	   return vendorRepo.findByEmailId(email);
   }
   public Vendor saveVendor(Vendor vendor)
   {
	   return vendorRepo.save(vendor);
   }
   public Users validateUser(String email,String password)
   {
	   return userRepo.findByEmailIdAndPassword(email,password);
   }
   public Admin validateAdmin(String email,String password)
   {
	   return adminRepo.findByEmailIdAndPassword(email,password);
   }
   public Vendor validateVendor(String email,String password)
   {
	   return vendorRepo.findByEmailIdAndPassword(email,password);
   }
   public Vehicles checkRegNo(String regNo)
   {
	   return vehicleRepo.findByRegNo(regNo);
   }
   public Vehicles addVehicle(Vehicles vehicle)
   {
	   return vehicleRepo.save(vehicle);
   }
   public List<Vehicles> searchForVehicles(String name)
   {
	   List<Vehicles>v= vehicleRepo.findByName(name);
	   List<Vehicles> vehicle=new ArrayList<Vehicles>();
	   for(Vehicles v1:v)
	   {
		   if(v1.getStatus().equals("Open"))
			   vehicle.add(v1);
	   }
	   return vehicle;
   }	
   public Vehicles getVehicleDetailsAndBook(long id) {
	// TODO Auto-generated method stub
	Optional<Vehicles> v=vehicleRepo.findById(id);
	if(v.isPresent()) {
        return v.get();
	} else {
	    return null;
	}
}
   	public Booking saveBooking(Booking booking) {
	
	return bookingRepo.save(booking);
}
   	public Vehicles updateVehicles(Vehicles vehicle) {
	return vehicleRepo.save(vehicle);
	
}
   	public List<Vendor> searchAllVendors() {
   		List<Vendor>v=(List<Vendor>) vendorRepo.findAll();
   		return v;
   		}
	public void deleteVendor(long vendorId) {
		 vendorRepo.deleteById(vendorId);	
		
	}
	public List<Booking> viewBookingsByVendorId(Long vendorId) {
		return bookingRepo.findByVendorId(vendorId);
	}
	
}
