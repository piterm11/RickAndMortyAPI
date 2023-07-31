package com.example.rickandmorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {

    private final RickAndMortyAPIService ramAPI;

    @Autowired
    public AppController(RickAndMortyAPIService rickAndMortyAPIService) {
        this.ramAPI = rickAndMortyAPIService;
    }

    @RequestMapping
    @ResponseBody
    public String defaultPage() {
        return """
                How to use:</br>
                Add to site link following text "/character/?name=" and after that write name of the character from Rick and Morty</br>
                example: <a href="/character/?name=Rick Sanchez">/character/?name=Rick Sanchez</a></br>
                Enjoy :)
                """;
    }

    @GetMapping(path = "/character/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String findCharacter(@RequestParam(value = "name") String name) {
        return ramAPI.findCharacter(name);
    }
}
