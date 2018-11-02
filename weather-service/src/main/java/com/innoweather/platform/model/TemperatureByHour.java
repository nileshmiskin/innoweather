package com.innoweather.platform.model;

import lombok.Value;

@Value
public class TemperatureByHour {
    private Integer hour;
    private Double tempInFahrenheit;
}
