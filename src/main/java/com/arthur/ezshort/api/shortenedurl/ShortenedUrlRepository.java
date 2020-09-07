package com.arthur.ezshort.api.shortenedurl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, Long> {

	@Query(value = "SELECT * FROM LINK WHERE sh_url=?1", nativeQuery = true)
	Optional<ShortenedUrl> findByShortUrl(String shortUrlParam);

	@Query(value = "SELECT * FROM LINK WHERE or_url=?1", nativeQuery = true)
	Optional<ShortenedUrl> findByOriginalUrl(String url);

}
