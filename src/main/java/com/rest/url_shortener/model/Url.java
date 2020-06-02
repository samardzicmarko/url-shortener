package com.rest.url_shortener.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Url {

    private @Id @GeneratedValue Long id;
    private String uri;
    private String shortUri;

    @ManyToOne
    @JoinColumn()
    private Account account;

    public Account getAccount(){
        return account;
    }
    public void setAccount(Account account){
        this.account = account;
    }

    public Url(){}

    Url(String Uri, String ShortUri){
        this.shortUri = ShortUri;
        this.uri = Uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getShortUri() {
        return shortUri;
    }

    public void setShortUri(String shortUri) {
        this.shortUri = shortUri;
    }

    public String generateRandomString(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(5);

        for (int i = 0; i<5; i++){
            int index = (int) (characters.length() * Math.random());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

}
