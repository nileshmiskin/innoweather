package com.innoweather.platform.model

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class ForecastTest extends Specification {

    @Unroll
    def "It should not return coolest hour of the day when forecast has no entries - hourWiseTemps: #hourWiseTemps"() {
        when: "A weather forecast for a day is given"
        Forecast forecast = new Forecast(LocalDate.now(), hourWiseTemps)
        then:
        !forecast.coolestHoursOfTheDay
        where:
        hourWiseTemps << [ [], null ]
    }

    def "It should return coolest hours of the day when forecast has multiple entries"() {
        when: "A weather forecast for a day is given"
        Forecast forecast = new Forecast(LocalDate.now(),
                [new TemperatureByHour(3, 281.19),
                 new TemperatureByHour(6, 280.01),
                 new TemperatureByHour(23, 280.012)])
        then:
        forecast.coolestHoursOfTheDay == [6]
    }

    def "It should return multiple coolest hours of the day when forecast has multiple entries for min temperature"() {
        when: "A weather forecast for a day is given"
        Forecast forecast = new Forecast(LocalDate.now(),
                [new TemperatureByHour(3, 281.19),
                 new TemperatureByHour(6, 280.01),
                 new TemperatureByHour(23, 280.01)])
        then:
        forecast.coolestHoursOfTheDay == [6, 23]
    }
}
