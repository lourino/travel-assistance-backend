package lourino.chemane.mz.travel.assistance.service.exchange.controller;


import lourino.chemane.mz.travel.assistance.service.exchange.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Lourino Chemane
 */
@RestController
@RequestMapping(path = "exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService service;

    @GetMapping("/EUR{currency}")
    public ResponseEntity<Object> getExchangeFromEuro(@RequestParam String currency) throws IOException {
        return ResponseEntity.ok(this.service.getExchangeFromEuro(currency));
    }
}
