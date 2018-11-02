package com.innoweather.platform.dao

import com.innoweather.platform.config.OpenWeatherMapConfig
import com.innoweather.platform.model.WeatherData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class OpenWeatherMapClientTest extends Specification {
    def "It fetches the forecast from weather API"(){
        given:
        RestTemplate restTemplate = Mock(RestTemplate)
        OpenWeatherMapConfig config = Stub(OpenWeatherMapConfig)
        OpenWeatherMapClient client = new OpenWeatherMapClient(restTemplate, config)
        and:
        config.getForecastApi() >> "http://forecast.com"
        when:
        client.getWeatherForecast("12345")
        then:
        1 * restTemplate.getForEntity(new URI("http://forecast.com?zip=12345,us"), WeatherData.class)
    }

    def "No forecast is returned when there is no data from weather API"(){
        given:
        RestTemplate restTemplate = Stub(RestTemplate)
        OpenWeatherMapConfig config = Stub(OpenWeatherMapConfig)
        OpenWeatherMapClient client = new OpenWeatherMapClient(restTemplate, config)
        and:
        config.getForecastApi() >> "http://forecast.com"
        restTemplate.getForEntity(new URI("http://forecast.com?zip=12345,us"), WeatherData.class) >> new ResponseEntity<WeatherData>(null, HttpStatus.OK)
        when:
        def forecast = client.getWeatherForecast("12345")
        then:
        !forecast
    }
}
