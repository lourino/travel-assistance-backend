package lourino.chemane.mz.travel.assistance.service.weather.implementation;

import com.google.gson.Gson;
import lourino.chemane.mz.travel.assistance.service.weather.WeatherService;
import lourino.chemane.mz.travel.assistance.service.weather.domain.WeatherAPIResponse;
import lourino.chemane.mz.travel.assistance.service.weather.domain.WeatherResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Lourino Chemane
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private final String charset = "UTF-8";
    private final String weatherAPIUrl = "https://api.openweathermap.org/data/3.0/onecall";
    private final String appid = "c7c41eed56f8d72daef5d9212c516066";

    @Override
    public WeatherResponse getCurrentWeatherForecast(String latitude, String longitude) throws IOException {
        WeatherAPIResponse weatherAPIResponse = callCurrentWeatherForecastAPI(latitude, longitude);
        String kelvin = weatherAPIResponse.getTemperature();
        String celsius = (Integer.parseInt(kelvin) - 273.15) + "";
        String fahrenheit =  ((9/5) * Integer.parseInt(celsius) + 32) + "";
        return new WeatherResponse(kelvin, celsius, fahrenheit);
    }

    private WeatherAPIResponse callCurrentWeatherForecastAPI(String latitude, String longitude) throws IOException {
        Gson gson = new Gson();
        String query = new StringBuilder()
                .append("?")
                .append(String.format("appid=%s", URLEncoder.encode(this.appid, this.charset)))
                .append("&")
                .append(String.format("lat=%s", URLEncoder.encode(latitude, this.charset)))
                .append("&")
                .append(String.format("lon=%s", URLEncoder.encode(longitude, this.charset)))
                .toString();
        String urlString = this.weatherAPIUrl + query;
        System.out.println("Url: " + urlString);
        URL url = new URL (urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), this.charset))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            int responseCode = con.getResponseCode();
            System.out.println("ResponseBody: " + response.toString());
            System.out.println("ResponseCode: " + responseCode);
        }

        Map responseMap = gson.fromJson(String.valueOf(response), Map.class);

        return new WeatherAPIResponse(((Map) responseMap.get("current")).get("temp").toString());
    }

}
