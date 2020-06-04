package com.rest.url_shortener.controllers;

import com.rest.url_shortener.model.Account;
import com.rest.url_shortener.model.Url;
import com.rest.url_shortener.repository.AccountRepository;
import com.rest.url_shortener.repository.UrlRepository;
import com.rest.url_shortener.services.GenerateRandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UrlController {

    @Autowired
    private final UrlRepository repository;
    @Autowired
    AccountRepository accountRepository;

    UrlController(UrlRepository repository){
        this.repository = repository;
    }

    public Map<String, Url> urlList = new HashMap<>();

    public void makeUrlShort(String randomChar, Url urlshortener) {
        urlshortener.setShortUri("localhost:8080/" + randomChar);
        urlshortener.setSetShortKey(randomChar);
        urlList.put(randomChar, urlshortener);
    }

    @GetMapping("/statistic")
    List<Url> all(){
        return repository.findAll();
    }

    @GetMapping("/statistic/{AccountId}")
    public ResponseEntity<Map<String, Integer>> getData(@PathVariable("AccountId") String AccountId) {
        Map<String, Integer> urls = new HashMap<>();
        Account urlList = accountRepository.findAccountByAccountId(AccountId);

        for(final Url url : urlList.getUrls()){

            urls.put(url.getUri(), url.getVisits());
        }
        return new ResponseEntity<>(urls, HttpStatus.OK);
    }

    @GetMapping(value = "/{path}", consumes = "application/json", produces="application/json")
    public void getFullUrl(HttpServletResponse response, @PathVariable("path") String Path) {
        HttpHeaders headers = new HttpHeaders();

        Url urlExist = repository.findUrlBySetShortKey(Path);

        if (urlExist != null){
            urlExist.setVisits(urlExist.getVisits()+1);
            repository.save(urlExist);

            headers.add("Location", urlExist.getUri());
            response.setHeader("Location", urlList.get(Path).getUri());
            response.setStatus(302);
        } else {
            headers.add("Location", "Not found");
            response.setStatus(404);
        }

    }


    @PostMapping(value = "/register", consumes = "application/json", produces="application/json")
    public Map<String, String> registerUrl(@RequestBody Url uri) {
        if (uri.getUri() == null){
            System.out.print(uri);
            HashMap<String, String> map = new HashMap<>();
            map.put("Status:", "Failed, uri is required");
            return map;
        } else {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String accName = auth.getName();
            Account current = accountRepository.findAccountByAccountId(accName);
            Url newUrl = new Url();
            newUrl.setAccount(current);
            newUrl.setUri(uri.getUri());

            HashMap<String, String> map = new HashMap<>();
            String randomSet = GenerateRandomString.make(6);
            makeUrlShort(randomSet, newUrl);
            map.put("shorted", newUrl.getShortUri());
            repository.save(newUrl);
            return map;

        }

    }

}
