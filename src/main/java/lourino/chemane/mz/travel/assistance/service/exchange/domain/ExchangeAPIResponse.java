package lourino.chemane.mz.travel.assistance.service.exchange.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Lourino Chemane
 */
@Setter
@AllArgsConstructor
@ToString
@Getter
public class ExchangeAPIResponse {

    private String success;
    private String timestamp;
    private String base;
    private String date;
    private Rates rates;
}
