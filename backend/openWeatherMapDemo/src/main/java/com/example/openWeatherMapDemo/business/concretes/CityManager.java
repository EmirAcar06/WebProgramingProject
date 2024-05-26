package com.example.openWeatherMapDemo.business.concretes;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.example.openWeatherMapDemo.business.abstracts.CityService;
import com.example.openWeatherMapDemo.business.requests.CreateCityRequest;
import com.example.openWeatherMapDemo.business.requests.UpdateCityRequest;
import com.example.openWeatherMapDemo.business.responses.GetAllCityResponse;
import com.example.openWeatherMapDemo.business.rules.CityBusinessRules;
import com.example.openWeatherMapDemo.core.utilities.mappers.ModelMapperService;
import com.example.openWeatherMapDemo.dataAccess.abstracts.CityRepository;
import com.example.openWeatherMapDemo.entities.concretes.City;

import lombok.AllArgsConstructor;

@Service

public class CityManager implements CityService{
	
	private CityRepository cityRepository;
	private ModelMapperService modelMapperService;
	private CityBusinessRules cityBusinessRules;
	
	public CityManager(CityRepository cityRepository, ModelMapperService modelMapperService,
			CityBusinessRules cityBusinessRules) {
		super();
		this.cityRepository = cityRepository;
		this.modelMapperService = modelMapperService;
		this.cityBusinessRules = cityBusinessRules;
	}
	private JsonParser jsonParser;
	


	@Override
	public List<GetAllCityResponse> getAll() {
		List<City> cities = cityRepository.findAll();
		List<GetAllCityResponse> cityResponse = cities.stream()
				.map(city->this.modelMapperService.forResponse()
						.map(city,GetAllCityResponse.class)).collect(Collectors.toList());
		return cityResponse;
	}

	@Override
	public void add(CreateCityRequest createCityRequest) {
		
		this.cityBusinessRules.checkIfCityNameExists(createCityRequest.getName());
		String strJson = JsonParser.getJSONFromURL("https://api.openweathermap.org/data/2.5/weather?q="+createCityRequest.getName()+"&mode=json&lang=en&units=metric&appid=a7ada9e4cf046c8e0366d43e10d90101");
		
		
		City city = this.modelMapperService.forRequest().map(createCityRequest,City.class);
		
		  
		 this.cityRepository.save(jsonParser.parse(city, strJson));
		
	}

	@Override
	public void update(UpdateCityRequest updateCityRequest) {
		City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityRepository.save(city);
	}

	@Override
	public void delete(int id) {
		this.cityRepository.deleteById(id);
		
	}

	@Override
	public City getByName(String name) {
		City city = this.cityRepository.getByName(name);
		
		return city;
	}

	

}
