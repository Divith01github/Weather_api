package com.example.weather_co;

import com.example.weather_co.Entity.WeatherInfo;
import com.example.weather_co.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class WeatherCoApplicationTests {

	@Autowired
	private WeatherService service;

	@Test
	void testWeatherFetch() {
		WeatherInfo info = service.getWeather("411014", LocalDate.of(2020, 10, 15));
		assertNotNull(info);
		assertEquals("411014", info.getPincode());
	}
}
