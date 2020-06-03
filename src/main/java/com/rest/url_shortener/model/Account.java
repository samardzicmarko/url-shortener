package com.rest.url_shortener.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Account {

    private @Id @GeneratedValue Long id;
    @Column(nullable = false)
    private String accountId;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Url> urlList;

    public Account(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    Account(String AccountId, String password, Set<Url> urlList){
        this.accountId = AccountId;
        this.password = generatePassword();
        this.urlList = urlList;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Set<Url> getUrls(){
        return urlList;
    }

    public void setUrlList(Set<Url> urlList){
        this.urlList = urlList;
    }

    public String generatePassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i<8; i++){
            int index = (int) (characters.length() * Math.random());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }


}
