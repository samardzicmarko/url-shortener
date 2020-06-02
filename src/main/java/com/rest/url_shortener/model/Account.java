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

    @OneToMany(mappedBy = "account")
    private Set<Url> urls;

    public Account(){}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    Account(String AccountId, String password){
        this.accountId = AccountId;
        this.password = generatePassword();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Set<Url> getUrls(){
        return urls;
    }

    public void setUrls(Set<Url> urls){
        this.urls = urls;
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
