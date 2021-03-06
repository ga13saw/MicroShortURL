package com.code4you.myurlshortener.services;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.code4you.myurlshortener.model.Url;
import com.code4you.myurlshortener.model.UrlDto;
import com.code4you.myurlshortener.repository.UrlRepository;
import com.google.common.hash.Hashing;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

	//private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);
	
	@Autowired
	private UrlRepository urlRepository;

	
	@Override
	public Url generateShortLink(UrlDto urlDto) {
	
		if (urlDto!=null && StringUtils.isNotEmpty(urlDto.getUrl())) {
			String encodedUrl = encodeUrl(urlDto.getUrl());
			Url urlToSave = new Url();
			urlToSave.setCreationDate(LocalDateTime.now());;
			urlToSave.setOriginalUrl(urlDto.getUrl());
			urlToSave.setShortLink(encodedUrl);
			urlToSave.setExpirationDate(getExpirationDate(urlDto.getExpirationDate(), urlToSave.getCreationDate()));
			Url urlToRet = saveShortLink(urlToSave);

			if (urlToRet != null)
				return urlToRet;

			return null;
		}
		return null;
	}

	
	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
		if (StringUtils.isBlank(expirationDate)) {
			return creationDate.plusSeconds(120);
		}
		LocalDateTime expirationDateToRet = LocalDateTime.parse(expirationDate);
		return expirationDateToRet;
	}

	
	public String encodeUrl(String url) {
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.adler32().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
		return encodedUrl;
	}

	@Override
	public Url saveShortLink(Url url) {
		Url urlToRet = urlRepository.save(url);
		return urlToRet;
	}

//	@Override
//	public Url getEncodedUrl(String url) {
//		Url urlToRet = urlRepository.findByShortLink(url);
//		return urlToRet;
//	}

//	@Override
//	public void deleteShortLink(Url url) {
//
//		urlRepository.delete(url);
//	}


	
}
