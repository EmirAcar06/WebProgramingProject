package com.example.openWeatherMapDemo.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.openWeatherMapDemo.entities.concretes.City;

public interface CityRepository extends JpaRepository<City, Integer>{
	
	boolean existsByName(String name);
	City getByName(String name);

}
