package com.carshop.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carshop.demo.models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
