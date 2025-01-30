package com.project.hw4.Controllers;


import com.project.hw4.Clients.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/status/{user}")
    public ResponseEntity<Map<String,Object>> getStatus( @PathVariable String user){
        return ResponseEntity.ok(currencyService.getStatusOfApi(user));
    }

    @GetMapping("/supported")
    public ResponseEntity<Map<String,Object>> getSupported(){
        return ResponseEntity.ok(currencyService.getSupportedCurrency());
    }

    @GetMapping("/convert")
    public ResponseEntity<String> convert(@RequestParam(value = "from", defaultValue = "USD") String CurrencyFrom,
                                                      @RequestParam(value = "to", defaultValue = "INR") String CurrencyTo,
                                                      @RequestParam(value = "units",defaultValue = "1.0") Double units ){


        return ResponseEntity.ok(currencyService.getRateOfCurrency(CurrencyFrom,CurrencyTo,units));
    }

}
