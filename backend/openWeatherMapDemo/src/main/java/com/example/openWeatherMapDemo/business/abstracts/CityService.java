package com.example.openWeatherMapDemo.business.abstracts;

import java.util.List;

import com.example.openWeatherMapDemo.business.requests.CreateCityRequest;
import com.example.openWeatherMapDemo.business.requests.UpdateCityRequest;
import com.example.openWeatherMapDemo.business.responses.GetAllCityResponse;
import com.example.openWeatherMapDemo.entities.concretes.City;

public interface CityService {
	City getByName(String name);
	List<GetAllCityResponse> getAll();
	void add(CreateCityRequest createCityRequest);
	void update(UpdateCityRequest updateCityRequest);
	void delete(int id);


}
