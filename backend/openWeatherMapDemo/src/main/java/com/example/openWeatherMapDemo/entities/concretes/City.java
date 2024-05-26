package com.example.openWeatherMapDemo.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="cities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="name")
	private String name;
	
	@Column(name = "lon")
	private double lon;
	
	@Column(name = "lat")
	private double lat;
	
	@Column(name ="country")
	private String country;
	
	@Column(name="weatherDescription")
	private String weatherDescription;
	
	@Column(name ="minTemp")
	private double minTemp;
	
	@Column(name ="maxTemp")
	private double maxTemp;
	
	@Column(name ="temp")
	private double temp;
	
	

}
