package com.rest.url_shortener.repository;

import com.rest.url_shortener.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsAccountByAccountId(String AccountId);
}
