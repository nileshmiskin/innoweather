package com.innoweather.platform.model

import spock.lang.Specification

import java.time.LocalDate

class WeatherDataEntryTest extends Specification {

    def "WeatherDataEntry should report hour and date correctly" (){
        when: "An epoch is given"
        WeatherDataEntry entry = new WeatherDataEntry(epoch: 1541157389L, temperatureEntry: new TemperatureEntry(tempInFahrenheit: 279.0D))
        then: "Ensure date and hour are calculated correctly"
        entry.date == LocalDate.of(2018, 11, 2)
        entry.hour == 16
    }
}
