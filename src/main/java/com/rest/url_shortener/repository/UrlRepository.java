package com.rest.url_shortener.repository;

import com.rest.url_shortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Url findUrlBySetShortKey(String ShortKey);

    boolean existsUrlByUri(String uri);

}
