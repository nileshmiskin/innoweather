package com.innoweather.platform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenWeatherMapConfig {
    @Value("${app.openweathermap.forecast-endpoint}")
    private String forecastApi;

    @Value("${app.openweathermap.app-id}")
    private String appId;

    public String getForecastApi() {
        return String.format(forecastApi, appId);
    }
}
