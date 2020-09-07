package com.arthur.ezshort.api.shortenedurl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ShortenedUrlService {

	@Autowired
	private ShortenedUrlRepository repository;

	@Value("${domain.baseurl}")
	private String BASE_URL;

	/**
	 * @param {@link
	 *            UrlDTO} with the original url
	 * @return shortened url
	 */
	public String createShortenedUrl(UrlDTO req) {
		return BASE_URL
				+ UrlUtils.encode(repository.save(req.toEntity()).getId());
	}

	/**
	 * @param Shortened
	 *            Url
	 * @return Original Url
	 */
	public String findUrl(String shortUrlParam) {
		final ShortenedUrl link = repository
				.findById(UrlUtils.decode(shortUrlParam)).orElse(null);

		if (link != null)
			return link.getOriginalUrl();

		return null;// TODO add exception for notfound and invalid urls
	}

}
