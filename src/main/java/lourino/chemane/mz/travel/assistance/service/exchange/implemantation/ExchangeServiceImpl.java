package lourino.chemane.mz.travel.assistance.service.exchange.implemantation;

import com.google.gson.Gson;
import lourino.chemane.mz.travel.assistance.service.exchange.ExchangeService;
import lourino.chemane.mz.travel.assistance.service.exchange.domain.Curency;
import lourino.chemane.mz.travel.assistance.service.exchange.domain.ExchangeAPIResponse;
import lourino.chemane.mz.travel.assistance.service.exchange.domain.ExchangeResponse;
import lourino.chemane.mz.travel.assistance.service.exchange.domain.Rates;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Lourino Chemane
 */
@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final String exchangeAPIUrl = "http://api.exchangeratesapi.io/v1/latest";
    private final String accessKey = "0cefc78e80449c2c40516c8b6fc0be11";
    private final String format = "1";
    private final String charset = "UTF-8";

    @Override
    public ExchangeResponse getExchangeFromEuro(String currency) throws IOException {
        ExchangeAPIResponse exchangeAPIResponse = callExchangeRatesAPI(currency);
        return new ExchangeResponse(exchangeAPIResponse.getRates().getCurency().getValue());
    }

    private ExchangeAPIResponse callExchangeRatesAPI(String currency) throws IOException {
        Gson gson = new Gson();
        String query = new StringBuilder()
                .append("?")
                .append(String.format("access_key=%s", URLEncoder.encode(this.accessKey, this.charset)))
                .append("&")
                .append(String.format("symbols=%s", URLEncoder.encode(currency, this.charset)))
                .append("&")
                .append(String.format("format=%s", URLEncoder.encode(this.format, this.charset)))
                .toString();
        String urlString = this.exchangeAPIUrl + query;
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

        return new ExchangeAPIResponse(
                responseMap.get("success").toString(),
                responseMap.get("timestamp").toString(),
                responseMap.get("base").toString(),
                responseMap.get("date").toString(),
                new Rates(
                        new Curency(
                                ((Map) responseMap.get("rates")).get(currency).toString()
                        )
                )
        );
    }
}
