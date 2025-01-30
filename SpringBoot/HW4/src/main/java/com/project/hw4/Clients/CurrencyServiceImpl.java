package com.project.hw4.Clients;


import com.project.hw4.Exceptions.NonAdminRequestException;
import com.project.hw4.Exceptions.ServiceProblemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final RestClient restClientStatus;

    private final RestClient restClientSupported;

    private final RestClient restClientRate;

    @Autowired
    public CurrencyServiceImpl(@Qualifier("restClientStatus") RestClient restClientStatus,
                               @Qualifier("restClientSupported") RestClient restClientSupported,
                               @Qualifier("restClientRate") RestClient restClientRate) {
        this.restClientStatus = restClientStatus;
        this.restClientSupported = restClientSupported;
        this.restClientRate = restClientRate;
    }

    public Map<String,Object> getStatusOfApi(String user){
        if("admin".equalsIgnoreCase(user)) {
            try{


                return restClientStatus
                        .get()
                        .retrieve()
//                      .body(new ParameterizedTypeReference<Map<String,Object>>(){});
                        .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {
                        })// it returns a ResponseEntity, as it is service we do not need ResponseEntity, instead we would just prefer body of the ResponseEntity
                        .getBody();
            }
            catch(Exception e){
                throw new ServiceProblemException("Some error in getStatusOfApi Occured");
            }
        }
        else{
            throw new NonAdminRequestException("Non Admin Requested this!!");
        }
    }

    public Map<String,Object> getSupportedCurrency(){
        try {
            return restClientSupported.
                    get()
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {
                    })
                    .getBody();
        }
        catch(Exception e){
            throw new ServiceProblemException("Some error in getSupportedCurrency Occured");
        }
    }

    public String getRateOfCurrency(String currencyFrom, String currencyTo,Double unit){

        try{
            Double value =0.0 ;
            Map<String,Object> AllCurrencies =  restClientSupported.
                    get()
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {
                    })
                    .getBody();
            System.out.println("Reached 1");
            if(AllCurrencies.containsKey("data")){
                System.out.println("Reached 2");
                Map<String,Object> currency = (Map<String,Object>) AllCurrencies.get("data");
                System.out.println("Reached 3");
                if(currency.containsKey(currencyFrom.toUpperCase()) && currency.containsKey(currencyTo.toUpperCase())){
                    System.out.println("Reached 4");
                    Map<String,Object> latestRate = restClientRate
                            .get()
                            //.uri("&base_currency={currencyFrom}",currencyFrom)
                            .uri(uriBuilder -> uriBuilder
                                    .queryParam("base_currency", currencyFrom)
                                    .build())
                            .retrieve()
                            .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {})
                            .getBody();
                    System.out.println("Reached 5");
                    Map<String,Object> finalRates = (Map<String, Object>) latestRate.get("data");
                    System.out.println("Reached 6");
                    Object rate = finalRates.get(currencyTo.toUpperCase());
                    System.out.println("Reached 7");
                    if(rate instanceof Number){
                        System.out.println("Reached 7");
                         value = ((Number)rate).doubleValue();
                    }

                    else {
                        System.out.println("Reached 8");
                        throw new ServiceProblemException("Couldn't get rate");
                    }
                    System.out.println("Reached 9");
                }
                else {
                    System.out.println("Reached 10");
                    throw new ServiceProblemException("Non Existing Currency is Requested!");
                }

            }
            else {
                System.out.println("Reached 11");
                throw new ServiceProblemException("Some Error in getting CurrentRate Occured");
            }
            System.out.println("Reached 12");
            return unit+" : " + currencyFrom + " is equal to  " + value*unit+ " : "+currencyTo  ;

        }
        catch(Exception e){
            System.out.println("Reached 13");
            throw new ServiceProblemException("Some error in getRateOfCurrency Occured");
        }



    }



}
