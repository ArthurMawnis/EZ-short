package com.arthur.ezshort.api;

import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.arthur.ezshort.api.dto.CreateShortnedUrlRequest;
import com.arthur.ezshort.api.exceptions.InvalidUrlException;
import com.arthur.ezshort.api.shortenedurl.ShortenedUrlService;
import com.arthur.ezshort.api.utils.UrlUtils;

@Transactional
@SpringBootTest(classes = ApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LinkTest {

    @Autowired
    private ShortenedUrlService service;

    @Test
    void smokeTest() {
	Assertions.assertTrue(true, "Everything working fine!");
    }

    @Test
    void should_create_new_shortenedUrl() {
	CreateShortnedUrlRequest dto = new CreateShortnedUrlRequest();
	dto.setUrl("http://www.bigboidomain.com");
	try {
	    Assertions.assertNotNull(service.createShortenedUrl(dto));
	} catch (InvalidUrlException e) {
	   fail(e.getMessage());
	}
    }

    @Test
    void should_throw_InvalidUrlException_when_invalid_longUrl() {
	CreateShortnedUrlRequest dto = new CreateShortnedUrlRequest();
	dto.setUrl("ht://www..site?yes");

	Assertions.assertThrows(InvalidUrlException.class, () -> service.createShortenedUrl(dto),
		"invalid long url should throw a exception");
    }

    @Test
    void should_retrieve_valid_long_url() {
	final String URL = "https://www.huuuuuuuuuuuuuuuuuuuge.com";

	CreateShortnedUrlRequest dto = new CreateShortnedUrlRequest();
	dto.setUrl(URL);
	final String shortenedUrl = service.createShortenedUrl(dto);
	final String[] split = shortenedUrl.split("/");
	// shortened url is made of baseUrl + encoded id;
	// the magical 'split[3]' refers to the id's position
	final String longUrl = service.findUrl(split[3]);

	Assertions.assertEquals(URL, longUrl);
    }

    @Test
    void should_encode_and_decode_id() {
	final Long id = 258L;

	final String hex = UrlUtils.encode(id);
	final Long result = UrlUtils.decode(hex);

	Assertions.assertEquals(id, result);
    }
}
