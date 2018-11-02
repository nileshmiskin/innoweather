package com.innoweather.sample;

import lombok.Data;

import java.util.List;

@Data
public class Forecast {
    private String date;
    private List<Integer> coolestHoursOfTheDay;
}
