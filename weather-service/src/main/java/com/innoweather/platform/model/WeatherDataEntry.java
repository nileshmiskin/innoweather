package com.innoweather.platform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class WeatherDataEntry {
    @JsonProperty("dt")
    private Long epoch;
    @JsonProperty("main")
    private TemperatureEntry temperatureEntry;

    public LocalDate getDate() {
        return Instant.ofEpochSecond(epoch).atZone(ZoneId.of("America/Chicago")).toLocalDate();
    }

    public Integer getHour() {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epoch), ZoneId.of("America/Chicago"));
        return dateTime.getHour();
    }
}
