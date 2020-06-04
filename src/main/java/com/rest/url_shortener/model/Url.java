package com.rest.url_shortener.model;

import javax.persistence.*;


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

}
