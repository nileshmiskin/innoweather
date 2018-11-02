package com.innoweather.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemperatureEntry {
    @JsonProperty("temp")
    private Double tempInFahrenheit;
}
