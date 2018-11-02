package com.innoweather.sample;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class Forecast {
    private LocalDate date;
    private List<Integer> coolestHoursOfTheDay;
}
