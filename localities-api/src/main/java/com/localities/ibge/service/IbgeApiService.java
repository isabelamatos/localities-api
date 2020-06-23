package com.localities.ibge.service;

import com.localities.ibge.model.IbgeCity;
import com.localities.ibge.model.IbgeState;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IbgeApiService {
    private static final String IBGE_STATES_PATH = "/estados/";
    private static final String IBGE_CITIES_PATH = "/municipios/";
    private static final String IBGE_LOCALITY_PATH = "/localidades";
    private static final String IBGE_API_URI = "https://servicodados.ibge.gov.br/api/v1";

    private final RestTemplate restTemplate;

    public IbgeApiService(final RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "getIbgeCitiesFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")})
    public Set<IbgeCity> getIbgeCitiesByName(final String cityName) {
        final URI uri = URI.create(IBGE_API_URI + IBGE_LOCALITY_PATH + IBGE_CITIES_PATH);
        final IbgeCity[] cities = restTemplate.getForEntity(uri, IbgeCity[].class).getBody();
        return Arrays.stream(cities)
                .filter(city -> city.getName().equalsIgnoreCase(cityName))
                .collect(Collectors.toSet());
    }

    @HystrixCommand(fallbackMethod = "getIbgeCitiesFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")})
    public Set<IbgeCity> getIbgeCities() {
        final String statesPipeSep = getIbgeStates().stream()
                .map(ibgeState -> String.valueOf(ibgeState.getId()))
                .collect(Collectors.joining("|"));

        final URI uri = URI.create(IBGE_API_URI + IBGE_LOCALITY_PATH + IBGE_STATES_PATH + encodeValue(statesPipeSep) + IBGE_CITIES_PATH);
        final IbgeCity[] cities = restTemplate.getForEntity(uri, IbgeCity[].class).getBody();
        return Set.of(cities);
    }

    private Set<IbgeState> getIbgeStates() {
        final URI uri = URI.create(IBGE_API_URI + IBGE_LOCALITY_PATH + IBGE_STATES_PATH);
        final IbgeState[] states = restTemplate.getForEntity(uri, IbgeState[].class).getBody();
        return Set.of(states);
    }

    private Set<IbgeCity> getIbgeCitiesFallback(String cityName) {
        return new HashSet<>();
    }

    private Set<IbgeCity> getIbgeCitiesFallback() {
        return new HashSet<>();
    }

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
        }

        return value;
    }
}