package com.example.rickandmorty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RickAndMortyApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnDefaultResponse() {
        assertEquals("""
                How to use:</br>
                Add to site link following text "/character/?name=" and after that write name of the character from Rick and Morty</br>
                example: <a href="/character/?name=Rick Sanchez">/character/?name=Rick Sanchez</a></br>
                Enjoy :)
                """, testRestTemplate.getForObject("http://localhost:" + port, String.class));
    }

    @Test
    void wrongParameterReturnsStatusBadRequest() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/character/", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void wrongParameterReturnsBadRequest() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/character/?test", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void naValueReturnsStatusOk() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/character/?name", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
