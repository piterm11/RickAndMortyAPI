package com.example.rickandmorty;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RickAndMortyAPIService {
    private final RestTemplate restTemplate;

    public RickAndMortyAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String findCharacter(String name) {
        String url = "https://rickandmortyapi.com/api/character/";
        String requestParam = "?name="+name;
        return restTemplate.getForObject(url+requestParam, String.class);
    }
}
