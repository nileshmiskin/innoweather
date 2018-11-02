package com.innoweather.platform.service;

import com.innoweather.platform.dao.OpenWeatherMapClient;
import com.innoweather.platform.model.Forecast;
import com.innoweather.platform.model.TemperatureByHour;
import com.innoweather.platform.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private OpenWeatherMapClient openWeatherMapClient;

    @Autowired
    public WeatherService(OpenWeatherMapClient openWeatherMapClient) {
        this.openWeatherMapClient = openWeatherMapClient;
    }

    public Forecast getWeatherForecast(LocalDate date, String zipCode) {
        WeatherData weatherData = openWeatherMapClient.getWeatherForecast(zipCode);
        if (weatherData == null || CollectionUtils.isEmpty(weatherData.getTemperatureList())) {
            return new Forecast(date, null);
        }

        List<TemperatureByHour> temperatureByHours = weatherData.getTemperatureList()
                .stream()
                .filter(entry -> entry.getDate().getDayOfWeek().equals(date.getDayOfWeek()))
                .map(entry -> new TemperatureByHour(entry.getHour(), entry.getTemperatureEntry().getTempInFahrenheit()))
                .sorted(Comparator.comparing(TemperatureByHour::getHour))
                .collect(Collectors.toList());

        return new Forecast(date, temperatureByHours);
    }
}
