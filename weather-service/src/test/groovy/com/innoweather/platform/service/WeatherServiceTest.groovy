package com.innoweather.platform.service

import com.innoweather.platform.dao.OpenWeatherMapClient
import com.innoweather.platform.model.TemperatureEntry
import com.innoweather.platform.model.WeatherData
import com.innoweather.platform.model.WeatherDataEntry
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class WeatherServiceTest extends Specification {

    def "The weather service should return temperature forecast based on the weather data"() {
        given: "Weather data"
        OpenWeatherMapClient weatherMapClient = Stub(OpenWeatherMapClient)
        WeatherService service = new WeatherService(weatherMapClient)
        WeatherData weatherData = new WeatherData(code: "code", count: 1, temperatureList: [new WeatherDataEntry(epoch: 1541157389L, temperatureEntry: new TemperatureEntry(tempInFahrenheit: 279.0D))])
        LocalDate foreCastDate = LocalDate.of(2018, 11, 2)
        and:
        weatherMapClient.getWeatherForecast("12345") >> weatherData

        when:
        def forecast = service.getWeatherForecast(foreCastDate, "12345")

        then:
        forecast.date == foreCastDate
        forecast.hourWiseTemperatures.size() == 1
        forecast.coolestHoursOfTheDay == [6]
    }

    @Unroll
    def "The weather service should return empty forecast if the weather data doesn't have temperature details"() {
        given: "Weather data"
        OpenWeatherMapClient weatherMapClient = Stub(OpenWeatherMapClient)
        WeatherService service = new WeatherService(weatherMapClient)
        WeatherData weatherData = new WeatherData(code: "code", count: 1, temperatureList: temperatureList)
        LocalDate foreCastDate = LocalDate.of(2018, 11, 2)
        and:
        weatherMapClient.getWeatherForecast("12345") >> weatherData

        when:
        def forecast = service.getWeatherForecast(foreCastDate, "12345")

        then:
        forecast.date == foreCastDate
        !forecast.hourWiseTemperatures
        !forecast.coolestHoursOfTheDay

        where:
        temperatureList << [[], null]
    }

    def "The weather service should return empty forecast if the weather data is not returned"() {
        given: "Weather data"
        OpenWeatherMapClient weatherMapClient = Stub(OpenWeatherMapClient)
        WeatherService service = new WeatherService(weatherMapClient)
        LocalDate foreCastDate = LocalDate.of(2018, 11, 2)
        and:
        weatherMapClient.getWeatherForecast("12345") >> null

        when:
        def forecast = service.getWeatherForecast(foreCastDate, "12345")

        then:
        forecast.date == foreCastDate
        !forecast.hourWiseTemperatures
        !forecast.coolestHoursOfTheDay

        where:
        temperatureList << [[], null]
    }
}
