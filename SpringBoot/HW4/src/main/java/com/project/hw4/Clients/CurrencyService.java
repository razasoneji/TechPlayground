package com.project.hw4.Clients;

import java.util.Map;

public interface CurrencyService {

    public Map<String,Object> getStatusOfApi(String user);
    public Map<String,Object> getSupportedCurrency();
    public String getRateOfCurrency(String currencyFrom, String currencyTo,Double unit);
}
