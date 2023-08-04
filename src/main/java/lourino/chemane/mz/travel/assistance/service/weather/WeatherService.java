package lourino.chemane.mz.travel.assistance.service.weather;

import lourino.chemane.mz.travel.assistance.service.weather.domain.WeatherResponse;

import java.io.IOException;

/**
 * @author Lourino Chemane
 */
public interface WeatherService {

    WeatherResponse getCurrentWeatherForecast(String latitude, String longitude) throws IOException;
}
