package com.rest.url_shortener.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Account {

    private @Id @GeneratedValue Long id;
    private String accountId;
    private String password;


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
