package com.example.openWeatherMapDemo.business.rules;

import org.springframework.stereotype.Service;

import com.example.openWeatherMapDemo.core.utilities.exception.BusinessException;
import com.example.openWeatherMapDemo.dataAccess.abstracts.CityRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CityBusinessRules {
	private CityRepository cityRepository;
	public void checkIfCityNameExists(String name) {
		if(this.cityRepository.existsByName(name))
		{
			throw new BusinessException("City name already exists");
		}

	}

}