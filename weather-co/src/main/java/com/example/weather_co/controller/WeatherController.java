package com.example.weather_co.controller;

import com.example.weather_co.Entity.WeatherInfo;
import com.example.weather_co.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping
    public ResponseEntity<WeatherInfo> fetchWeather(@RequestParam String pincode, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        WeatherInfo weather = service.getWeather(pincode, localDate);
        return ResponseEntity.ok(weather);
    }
}
