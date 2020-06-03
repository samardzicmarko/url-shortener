package com.rest.url_shortener.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Url {

    private @Id @GeneratedValue Long id;
    private String uri;
    private String shortUri;
    private String setShortKey;

    public String getSetShortKey() {
        return setShortKey;
    }

    public void setSetShortKey(String setShortKey) {
        this.setShortKey = setShortKey;
    }

    private int visits;
    private int redirectType;

    @ManyToOne()
    private Account account;

    public Account getAccount(){
        return account;
    }
    public void setAccount(Account account){
        this.account = account;
    }

    public Url(){}

    public int getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(int redirectType) {
        this.redirectType = redirectType;
    }

    Url(Long id, String Uri, String ShortUri){
        this.id = id;
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

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
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

    @ManyToOne
    private Account manyToOne;

    public Account getManyToOne() {
        return manyToOne;
    }

    public void setManyToOne(Account manyToOne) {
        this.manyToOne = manyToOne;
    }
}
