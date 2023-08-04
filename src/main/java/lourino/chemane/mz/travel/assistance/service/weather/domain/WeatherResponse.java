package lourino.chemane.mz.travel.assistance.service.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class WeatherResponse {

    private final String kelvin;
    private final String celsius;
    private final String fahrenheit;

}
