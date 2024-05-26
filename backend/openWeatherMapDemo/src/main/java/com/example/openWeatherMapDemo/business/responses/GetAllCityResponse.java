package com.example.openWeatherMapDemo.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCityResponse {
	
	private int id;
	private String name;
	private double lon;
	private double lat;
	private String country;
	private String weatherDescription;
	private double minTemp;
	private double maxTemp;
	private double temp;

}
