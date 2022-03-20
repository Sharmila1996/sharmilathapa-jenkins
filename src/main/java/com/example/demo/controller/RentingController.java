package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Admin;
import com.example.demo.model.Booking;
import com.example.demo.model.Users;
import com.example.demo.model.Vehicles;
import com.example.demo.model.Vendor;
import com.example.demo.service.RentingService;

@RestController
public class RentingController {

	@Autowired
	private RentingService service;
	@PostMapping("/loginUser")
	@CrossOrigin(origins= "http://localhost:4200")
	public Users loginUser(@RequestBody Users user) throws Exception
	{
		String email=user.getEmailId();
		String password=user.getPassword();
		Users u=null;
		if(email!=null && !"".equals(email))
		{
		u=service.validateUser(email, password);
		if(u==null)
			throw new Exception("User does not exist");
		}
		return u;
	}
	@PostMapping("/loginAdmin")
	@CrossOrigin(origins= "http://localhost:4200")
	public Admin loginAdmin(@RequestBody Admin admin) throws Exception
	{
		String email=admin.getEmailId();
		String password=admin.getPassword();
		Admin a=null;
		if(email!=null && !"".equals(email))
		{
		a=service.validateAdmin(email, password);
		if(a==null)
			throw new Exception("User does not exist");
		}
		return a;
	}
	@PostMapping("/loginVendor")
	@CrossOrigin(origins= "http://localhost:4200")
	public Vendor loginVendor(@RequestBody Vendor vendor) throws Exception
	{
		String email=vendor.getEmailId();
		String password=vendor.getPassword();
		Vendor v=null;
		if(email!=null && !"".equals(email))
		{
		v=service.validateVendor(email, password);
		if(v==null)
			throw new Exception("User does not exist");
		}
		return v;
	}
	@PostMapping("/registerUser")
	@CrossOrigin(origins= "http://localhost:4200")
	public Users registerUser(@RequestBody Users user) throws Exception
	{
		
		String email=user.getEmailId();
		System.out.println(email);
		if(email!=null && !"".equals(email))
		{
			Users u=service.checkUserByEmail(email);
			if(u!=null)
				throw new Exception("user already exists");
		}
		Users userObj=service.saveUser(user);
		return userObj;
	}
	@PostMapping("/registerAdmin")
	@CrossOrigin(origins= "http://localhost:4200")
	public Admin registerAdmin(@RequestBody Admin admin) throws Exception
	{
		String email=admin.getEmailId();
		if(email!=null && !"".equals(email))
		{
			Admin u=service.checkAdminByEmail(email);
			if(u!=null)
				throw new Exception("user already exists");
		}
		Admin adminObj=service.saveAdmin(admin);
		return adminObj;
	}
	@PostMapping("/registerVendor")
	@CrossOrigin(origins= "http://localhost:4200")
	public Vendor registerVendor(@RequestBody Vendor vendor) throws Exception
	{
		String email=vendor.getEmailId();
		if(email!=null && !"".equals(email))
		{
			Vendor v=service.checkVendorByEmail(email);
			if(v!=null)
				throw new Exception("user already exists");
		}
		Vendor vendorObj=service.saveVendor(vendor);
		return vendorObj;
	}
	
	@PostMapping("/addVehicles")
	@CrossOrigin(origins= "http://localhost:4200")
	public Vehicles addVehicleDetails(@RequestBody Vehicles vehicle) throws Exception
	{
		String regNo=vehicle.getRegNo();
		
			Vehicles v=service.checkRegNo(regNo);
			if(v!=null)
				throw new Exception("vehicle with registration number"+regNo+"already exists");
		
		Vehicles vehicleObj=service.addVehicle(vehicle);
		return vehicleObj;
	}
	@GetMapping("/searchVehicles/{name}")
	@CrossOrigin(origins= "http://localhost:4200")
	public List<Vehicles> searchVehicles(@PathVariable String name)
	{
		List<Vehicles> v=service.searchForVehicles(name);
		return v;
	}
	@GetMapping("/searchDetails/{id}")
	@CrossOrigin(origins= "http://localhost:4200")
	public Vehicles bookVehicles(@PathVariable long id)
	{
		Vehicles vehicle=service.getVehicleDetailsAndBook(id);
		return vehicle;
	}
	@PostMapping("/addBookingDetails")
	@CrossOrigin(origins= "http://localhost:4200")
	public Booking addBookingDetails(@RequestBody Booking booking) throws Exception
	{
		Booking book=service.saveBooking(booking);
		long vehicleId=booking.getVehicleId();
		System.out.println(vehicleId);
		Vehicles vehicle=service.getVehicleDetailsAndBook(vehicleId);
		System.out.println(vehicle.getId());
		vehicle.setStatus("Close");
		service.updateVehicles(vehicle);
		return book;
	}
	@GetMapping("/viewVendors")
	@CrossOrigin(origins= "http://localhost:4200")
	public List<Vendor> viewVendors()
	{
		List<Vendor> vendor=service.searchAllVendors();
		return vendor;
	}
	@DeleteMapping("/deleteVendors/{id}")
	@CrossOrigin(origins= "http://localhost:4200")
	public void deleteVendors(@PathVariable long id)
	{
		System.out.println(id);
		service.deleteVendor(id);
	}
	@GetMapping("/viewBookings/{id}")
	@CrossOrigin(origins= "http://localhost:4200")
	public List<Booking> viewBookings(@PathVariable long id)
	{
		List<Booking> booking=service.viewBookingsByVendorId(id);
		return booking;
	}
	@PostMapping("/changeStatus")
	@CrossOrigin(origins= "http://localhost:4200")
	public Booking changeStatus(@RequestBody Booking booking) throws Exception
	{
		long id=booking.getVehicleId();
		System.out.println(id);
		Vehicles v=service.getVehicleDetailsAndBook(id);
		v.setStatus("Open");
		service.updateVehicles(v);
		return booking;
	}
}
	

