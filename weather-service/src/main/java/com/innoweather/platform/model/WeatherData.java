package com.innoweather.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherData {
    @JsonProperty("cod")
    private String code;
    @JsonProperty("cnt")
    private Integer count;
    @JsonProperty("list")
    private List<WeatherDataEntry> temperatureList;
}
