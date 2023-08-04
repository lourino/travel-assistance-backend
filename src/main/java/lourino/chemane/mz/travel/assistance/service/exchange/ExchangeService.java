package lourino.chemane.mz.travel.assistance.service.exchange;

import lourino.chemane.mz.travel.assistance.service.exchange.domain.ExchangeAPIResponse;
import lourino.chemane.mz.travel.assistance.service.exchange.domain.ExchangeResponse;

import java.io.IOException;

/**
 * @author Lourino Chemane
 */
public interface ExchangeService {

    ExchangeResponse getExchangeFromEuro(String currency) throws IOException;
}
