package com.rest.url_shortener.services;

import com.rest.url_shortener.model.Account;
import com.rest.url_shortener.repository.AccountRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
@Component
public class AuthenticateAccount implements AuthenticationProvider {

    private final AccountRepository repository;

    AuthenticateAccount(AccountRepository repository){
        this.repository = repository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{

        if (authentication.isAuthenticated())
            return authentication;

        String accountId = authentication.getName();
        String password = authentication.getCredentials().toString();

        Account checkAcc = repository.findAccountByAccountId(accountId);

        if (checkAcc == null ){
            throw new BadCredentialsException("Account not found");
        }
        if (!checkAcc.getPassword().equals(password)){
            throw new BadCredentialsException("Bad credientials");
        }

        ArrayList<GrantedAuthority> granted = new ArrayList<>();
        granted.add(new SimpleGrantedAuthority("user"));
        return new UsernamePasswordAuthenticationToken(accountId, password, granted);

    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
