package com.project.hw4.Configs;

import com.project.hw4.Exceptions.ClientSideException;
import com.project.hw4.Exceptions.ServerSideException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig {

    @Value("${api.key}")
    private String apiKey;

    @Value("${base.url.status}")
    private String baseUrlStatus;

    @Value("${base.url.supported}")
    private String baseUrlSupported;

    @Value("${base.url.rate}")
    private String baseUrlRate;



    @Bean
    @Qualifier("restClientStatus")
    RestClient restClientStatus() {
        return RestClient.builder()
                .baseUrl(baseUrlStatus+"?apikey="+apiKey)
                .defaultHeader("Accept", "application/json")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError,((request, response) ->
                {
                    throw new ClientSideException("Error from the Client Side such as : bad URL, incorrect syntax, or unauthorized access, rather than a problem with the server itself");
                }))
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,((request, response) -> {
                    throw new ServerSideException("Error from the server side");
                }))
                .build();
    }
    @Bean
    @Qualifier("restClientSupported")
    RestClient restClientSupported() {
        return RestClient.builder()
                .baseUrl(baseUrlSupported+"?apikey="+apiKey)
                .defaultHeader("Accept", "application/json")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError,((request, response) ->
                {
                    throw new ClientSideException("Error from the Client Side such as : bad URL, incorrect syntax, or unauthorized access, rather than a problem with the server itself");
                }))
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,((request, response) -> {
                    throw new ServerSideException("Error from the server side");
                }))
                .build();
    }

    @Bean
    @Qualifier("restClientRate")
    RestClient restClientRate() {
        return RestClient.builder()
                .baseUrl(baseUrlRate+"?apikey="+apiKey)
                .defaultHeader("Accept", "application/json")
                .defaultStatusHandler(HttpStatusCode::is4xxClientError,((request, response) ->
                {
                    throw new ClientSideException("Error from the Client Side such as : bad URL, incorrect syntax, or unauthorized access, rather than a problem with the server itself");
                }))
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,((request, response) -> {
                    throw new ServerSideException("Error from the server side");
                }))
                .build();
    }


}
