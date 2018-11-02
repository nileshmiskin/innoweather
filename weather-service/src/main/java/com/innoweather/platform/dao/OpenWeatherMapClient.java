package com.innoweather.platform.dao;

import com.innoweather.platform.config.OpenWeatherMapConfig;
import com.innoweather.platform.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class OpenWeatherMapClient {

    private RestTemplate restTemplate;
    private OpenWeatherMapConfig config;

    @Autowired
    public OpenWeatherMapClient(RestTemplate restTemplate, OpenWeatherMapConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public WeatherData getWeatherForecast(String zipCode) {
        URI forecastUri = UriComponentsBuilder.fromUriString(config.getForecastApi()).queryParam("zip", zipCode+",us").build().toUri();
        ResponseEntity<WeatherData> entity = restTemplate.getForEntity(forecastUri, WeatherData.class);
        return entity == null ? null : entity.getBody();
    }
}
