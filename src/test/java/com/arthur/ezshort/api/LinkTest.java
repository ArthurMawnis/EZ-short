package com.arthur.ezshort.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.arthur.ezshort.api.shortenedurl.ShortenedUrlService;
import com.arthur.ezshort.api.shortenedurl.UrlDTO;
import com.arthur.ezshort.api.shortenedurl.UrlUtils;

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
		UrlDTO dto = new UrlDTO();
		dto.setUrl("http://www.google.com");
		service.createShortenedUrl(dto);
	}

	@Test
	void should_retrieve_valid_long_url() {
		service.findUrl("5");
	}

	@Test
	void should_encode_and_decode_id() {
		final Long id = 258L;

		final String hex = UrlUtils.encode(id);
		final Long result = UrlUtils.decode(hex);

		Assertions.assertEquals(id, result);
	}
}
