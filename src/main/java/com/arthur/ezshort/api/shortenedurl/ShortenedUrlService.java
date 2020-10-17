package com.arthur.ezshort.api.shortenedurl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arthur.ezshort.api.dto.CreateShortnedUrlRequest;
import com.arthur.ezshort.api.exceptions.InvalidUrlException;
import com.arthur.ezshort.api.utils.UrlUtils;

@Service
public class ShortenedUrlService {

    @Autowired
    private ShortenedUrlRepository repository;

    @Value("${domain.baseurl}")
    private String BASE_URL;

    /**
     * @param {@link CreateShortnedUrlRequest} with the original url
     * @return shortened url
     * @throws InvalidUrlException
     */
    public String createShortenedUrl(CreateShortnedUrlRequest req) {
	if (!UrlUtils.isValid(req.getUrl())) {
	    throw new InvalidUrlException("This url is not in a valid format.");
	}

	return BASE_URL + UrlUtils.encode(repository.save(req.toEntity()).getId());
    }

    /**
     * @param Shortened Url
     * @return Original Url
     */
    public String findUrl(String shortUrlParam) {
	final ShortenedUrl link = repository.findById(UrlUtils.decode(shortUrlParam)).orElse(null);

	if (link != null)
	    return link.getOriginalUrl();

	return null;// TODO add exception for notfound and invalid urls
    }

}
