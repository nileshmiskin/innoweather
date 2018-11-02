package com.innoweather.test.functional

import java.time.LocalDate

class WeatherForecastSpec extends AppSpec {

    def "It should fetch weather forcast for tomorrow and report coolest hour" (){
        given: "A zipcode of a US city"
        def zipCode = "84044"
        def tomorrow = LocalDate.now().plusDays(1)
        when: "I call the weather API"
        def response = http.get(path: "/forecast", query: ["zipCode": zipCode])
        then: "It should return a forecast for tomorrow along with coolest hour of the day"
        with(response){
            responseInfo.status == 200
            data.date == tomorrow.toString()
            data.hourWiseTemperatures
            data.coolestHoursOfTheDay.each({
                hour -> hour in (0..23)
            })
        }
    }

    def "It should error out if zip code is improper" (){
        given: "An invalid zip code"
        def zipCode = "840"
        when: "I call the weather API"
        def response = http.get(path: "/forecast", query: ["zipCode": zipCode])
        then: "It should return an error"
        with(response){
            responseInfo.status == 400
            error.errorCode == "VALIDATION_ERROR"
            error.errorMessage == "getForecast.zipCode: must be valid 5 digit zip code"
        }
    }

    def "It should report Not Found for zip code is of proper format but doesn't correspond to a city." (){
        given: "An invalid zip code having proper format"
        def zipCode = "00000"
        when: "I call the weather API"
        def response = http.get(path: "/forecast", query: ["zipCode": zipCode])
        then: "It should return an error"
        with(response){
            responseInfo.status == 404
            error.errorCode == "APP_ERROR"
            error.errorMessage == "404 Not Found"
        }
    }

}