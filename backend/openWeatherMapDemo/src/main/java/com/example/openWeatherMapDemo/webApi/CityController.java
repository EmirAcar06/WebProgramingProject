package com.example.openWeatherMapDemo.webApi;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.openWeatherMapDemo.business.abstracts.CityService;
import com.example.openWeatherMapDemo.business.requests.CreateCityRequest;
import com.example.openWeatherMapDemo.business.requests.UpdateCityRequest;
import com.example.openWeatherMapDemo.business.responses.GetAllCityResponse;
import com.example.openWeatherMapDemo.entities.concretes.City;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cities")
@AllArgsConstructor
@CrossOrigin
public class CityController {
	private CityService cityService;
	@GetMapping
	public List<GetAllCityResponse> getAll()
	{
		return cityService.getAll();
	}

	@GetMapping("/{name}")
	public City getByName(@PathVariable String name){
		return cityService.getByName(name) ;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody() @Valid() CreateCityRequest createCityRequest)
	{
		this.cityService.add(createCityRequest);
	}
	
	@PutMapping
	public void update(@RequestBody() UpdateCityRequest updateCityRequest)
	{
		this.cityService.update(updateCityRequest);
	}
	@DeleteMapping("/{id}")
	public void delete (@PathVariable int id)
	{
		this.cityService.delete(id);
		
	}
	

}
