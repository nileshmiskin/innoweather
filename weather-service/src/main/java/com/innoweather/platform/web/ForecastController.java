package com.innoweather.platform.web;

import com.innoweather.platform.service.WeatherService;
import com.innoweather.platform.model.Forecast;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@RequestMapping(value = "/forecast")
@Validated
@Api(description = "An API to provide tomorrow's temperatures forecast")
public class ForecastController {

    public WeatherService weatherService;

    @Autowired
    public ForecastController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get tomorrow's forecast", notes = "Fetches tomorrow's predicted temperatures and reports coolest hour of the day.")
    public Forecast getForecast(@RequestParam(value = "zipCode") @Pattern(regexp = "^[0-9]{5}$", message = "must be valid 5 digit zip code")
                                    @ApiParam(value = "zip code of a city in US", example = "84044", required = true) String zipCode) {
        LocalDate tomorrow = LocalDate.now(ZoneId.of("America/Chicago")).plusDays(1);
        Forecast forecast = weatherService.getWeatherForecast(tomorrow, zipCode);
        return forecast;
    }
}
