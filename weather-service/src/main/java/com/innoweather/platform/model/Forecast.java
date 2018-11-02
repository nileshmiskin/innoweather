package com.innoweather.platform.model;

import lombok.Value;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Value
public class Forecast {
    private LocalDate date;
    private List<TemperatureByHour> hourWiseTemperatures;

    public List<Integer> getCoolestHoursOfTheDay() {
        if (hourWiseTemperatures == null || hourWiseTemperatures.size() == 0) {
            return null;
        }
        Map<Double, List<TemperatureByHour>> tempHoursGroupedByTemp = hourWiseTemperatures.stream()
                .collect(Collectors.groupingBy(TemperatureByHour::getTempInFahrenheit));

        Map.Entry<Double, List<TemperatureByHour>> min = Collections.min(tempHoursGroupedByTemp.entrySet(), Comparator.comparing(Map.Entry::getKey));
        List<Integer> hours = min.getValue().stream().map(TemperatureByHour::getHour).collect(Collectors.toList());
        return hours;
    }
}
