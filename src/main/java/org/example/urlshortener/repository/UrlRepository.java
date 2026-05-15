package org.example.urlshortener.repository;
import org.example.urlshortener.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortCode(String shortCode);
    @Modifying
    @Transactional
    void deleteByShortCode(String shortCode);

    @Modifying
    @Transactional
    @Query("UPDATE ShortUrl u SET u.clicksCount = u.clicksCount + 1 WHERE u.shortCode = :code")
    void incrementClicks(@Param("code") String code);
}