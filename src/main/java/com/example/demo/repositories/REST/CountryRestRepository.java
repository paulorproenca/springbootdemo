package com.example.demo.repositories.REST;

import java.net.ConnectException;
import java.util.Collections;
import java.util.Optional;

import com.example.demo.datamodel.REST.CountryRestDTO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Repository
public class CountryRestRepository {


    public Optional<CountryRestDTO> findByCode(String code) {

        WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8090") // devia ser definido por configuração, e.g. em application.properties, Bean ou ser descoberto automaticamente
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
        .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8090"))
        .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
        .build();

        CountryRestDTO countryRestDTO;
        try {
            countryRestDTO = webClient
                .get()
                .uri("/country/" + code) // idem configuração
                .retrieve()

                // uma das formas de lidar com erros
                .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                .bodyToMono(CountryRestDTO.class)

                .onErrorReturn( null )

                .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                .block();
        }
        catch( Exception e) {

            countryRestDTO = null;
        }

        if( countryRestDTO != null )
            return Optional.of(countryRestDTO);
        else
            return Optional.empty();
    }
}
