package com.innoweather.platform.web

import com.innoweather.platform.service.WeatherService
import spock.lang.Specification

import java.time.LocalDate

class ForecastControllerTest extends Specification {

    def "Ensure that weather service is invoked to get tomorrow's temperature data"(){
        given:
        WeatherService weatherService = Mock(WeatherService)
        ForecastController controller = new ForecastController(weatherService)
        LocalDate tomorrow = LocalDate.now().plusDays(1)
        when:
        controller.getForecast("12345")
        then:
        1 * weatherService.getWeatherForecast(tomorrow, "12345")
    }
}
