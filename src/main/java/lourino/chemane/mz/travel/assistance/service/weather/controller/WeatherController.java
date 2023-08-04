package lourino.chemane.mz.travel.assistance.service.weather.controller;

import lourino.chemane.mz.travel.assistance.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping("/city{latitude}{longitude}")
    public ResponseEntity<Object> getWeatherForecast(
            @RequestParam String latitude,
            @RequestParam String longitude) throws IOException {
        return ResponseEntity.ok(this.service.getCurrentWeatherForecast(latitude, longitude));
    }
}
