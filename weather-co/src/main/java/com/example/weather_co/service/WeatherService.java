package com.example.weather_co.service;

import com.example.weather_co.Entity.Location;
import com.example.weather_co.Entity.WeatherInfo;
import com.example.weather_co.repositories.LocationRepository;
import com.example.weather_co.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    @Autowired private LocationRepository locationRepo;
    @Autowired private WeatherRepository weatherRepo;
    @Autowired private RestTemplate restTemplate;

    private final String geoApiTemplate = "https://api.openweathermap.org/geo/1.0/zip?zip=%s,IN&appid=%s";
    private final String weatherApiTemplate = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s&units=metric";

    public WeatherInfo getWeather(String pincode, LocalDate date) {
        return weatherRepo.findByPincodeAndDate(pincode, date).orElseGet(() -> {
            Location location = locationRepo.findByPincode(pincode).orElseGet(() -> {
                String geoUrl = String.format(geoApiTemplate, pincode, apiKey);
                Map<String, Object> geoRes = restTemplate.getForObject(geoUrl, Map.class);

                Location loc = new Location();
                loc.setPincode(pincode);
                loc.setLatitude(Double.parseDouble(geoRes.get("lat").toString()));
                loc.setLongitude(Double.parseDouble(geoRes.get("lon").toString()));
                return locationRepo.save(loc);
            });

            String weatherUrl = String.format(weatherApiTemplate, location.getLatitude(), location.getLongitude(), apiKey);
            Map<String, Object> weatherRes = restTemplate.getForObject(weatherUrl, Map.class);

            WeatherInfo info = new WeatherInfo();
            info.setPincode(pincode);
            info.setDate(date);
            info.setTemperature(Double.parseDouble(((Map<String, Object>) weatherRes.get("main")).get("temp").toString()));
            info.setDescription(((Map<String, Object>) ((List<?>) weatherRes.get("weather")).get(0)).get("description").toString());

            return weatherRepo.save(info);
        });
    }
}
