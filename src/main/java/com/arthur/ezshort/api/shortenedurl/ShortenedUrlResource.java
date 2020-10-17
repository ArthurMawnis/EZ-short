package com.arthur.ezshort.api.shortenedurl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.ezshort.api.dto.CreateShortnedUrlRequest;

@RestController
@RequestMapping("/")
public class ShortenedUrlResource {

    @Autowired
    private ShortenedUrlService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final CreateShortnedUrlRequest req) {
	return ResponseEntity.status(HttpStatus.OK).body(service.createShortenedUrl(req));
    }

    @GetMapping(path = "{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> redirect(@PathVariable(name = "token") String token) {
	final String originalUrl = service.findUrl(token);
	return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }

}
