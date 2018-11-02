package com.innoweather.sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class AppRunner implements CommandLineRunner {

    private RestTemplate restTemplate;
    private String weatherApiEndpoint;

    public AppRunner(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        weatherApiEndpoint = "http://localhost:8080/forecast";
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to Innoweather Sample App");
        System.out.print("Please enter zipcode of a city in the US: ");
        Scanner scanner = new Scanner(System.in);
        String zipCode = scanner.nextLine();
        try {
            Forecast forecast = restTemplate.getForObject(weatherApiEndpoint + "?zipCode=" + zipCode, Forecast.class);
            System.out.println("Forecast for "+zipCode+" is "+forecast);
        }catch (HttpClientErrorException ex){
            System.out.println("Something went wrong: "+ex.getResponseBodyAsString());
        }catch (Exception ex){
            System.out.println("Something went wrong. Maybe the forecast API is not available.");
        }

    }
}
