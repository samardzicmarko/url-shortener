package com.rest.url_shortener.controllers;

import com.rest.url_shortener.model.Url;
import com.rest.url_shortener.repository.UrlRepository;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@RestController
public class UrlController {

    private final UrlRepository repository;


    UrlController(UrlRepository repository){
        this.repository = repository;
    }

    public Map<String, Url> urlList = new HashMap<>();

    public void makeUrlShort(String randomChar, Url urlshortener) {
        urlshortener.setShortUri("localhost:8080/" + randomChar);
        urlList.put(randomChar, urlshortener);
    }

    @GetMapping("/statistic")
    List<Url> all(){
        return repository.findAll();
    }

    /*
    @GetMapping("/statistic/{AccountId}")
    public Map<String, Number> getData(@PathVariable("AccountId") String accID) throws IOException {

    }*/

    @GetMapping(value = "/{path}")
    public void getFullUrl(HttpServletResponse response, @PathVariable("path") String Path) throws IOException {
        response.sendRedirect(urlList.get(Path).getUri());
    }

    @PostMapping(value = "/register", consumes = "application/json", produces="application/json")
    public Map<String, String> registerUrl( @RequestBody Url uri) {

        if (uri.getUri() == null){
            System.out.print(uri);
            HashMap<String, String> map = new HashMap<>();
            map.put("Status:", "Failed, URL is required");
            return map;
        } else {
            Url newUrl = new Url();
            newUrl.setUri(uri.getUri());
            HashMap<String, String> map = new HashMap<>();
            map.put("Succes: ", "True");
            String randomSet = uri.generateRandomString();
            makeUrlShort(randomSet, newUrl);
            map.put("Url: ", newUrl.getUri());
            map.put("shorted", newUrl.getShortUri());
            repository.save(newUrl);

            return map;
        }

    }

}
