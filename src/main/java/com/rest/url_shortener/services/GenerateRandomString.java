package com.rest.url_shortener.services;

public class GenerateRandomString {

    public static String make(int n){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i<n; i++){
            int index = (int) (characters.length() * Math.random());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
