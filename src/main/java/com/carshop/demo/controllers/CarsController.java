package com.carshop.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.carshop.demo.models.Car;
import com.carshop.demo.repositories.CarRepository;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {
	
	@Autowired
	private CarRepository carRepository;
	
	
	@PostMapping
	public void create(@RequestBody Car vehicule) {
		this.carRepository.save(vehicule);
	}
	
	@GetMapping("{id}")
	public Optional<Car> getOne(@PathVariable("id") Long id) {
		Optional<Car> vehicle = this.carRepository.findById(id);
		if(!vehicle.isPresent()) {
			throw new CarNotFoundException("The car with the given id was not founded");
		}
		return vehicle;
	}
	
	@GetMapping
	public List<Car> getAll() {
		return this.carRepository.findAll();
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Car> update(@PathVariable("id") Long id, @RequestBody Car vehicle) {
		Optional<Car> vehicleFounded = this.carRepository.findById(id);
		if(!vehicleFounded.isPresent()) {
			throw new CarNotFoundException("The car with the given id was not founded");
		}
		vehicleFounded.get().setModel(vehicle.getModel());
		
		 vehicleFounded.get().setModel(vehicle.getModel());
		 vehicleFounded.get().setPlate(vehicle.getPlate());
		 vehicleFounded.get().setYear(vehicle.getYear());
		 this.carRepository.save(vehicleFounded.get());
		 return vehicleFounded;
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestParam Long id) {
		Optional<Car> vehicleFounded = this.carRepository.findById(id);
		if(!vehicleFounded.isPresent()) {
			throw new CarNotFoundException("The car with the given id was not founded");
		}
		this.carRepository.deleteById(id);
	}
	
	
	
	
}
