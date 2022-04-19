package com.code4you.myurlshortener.services;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.code4you.myurlshortener.constants.Constants;
import com.code4you.myurlshortener.model.Url;
import com.code4you.myurlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

	private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);
	
	@Autowired
	private UrlRepository urlRepository;

	@Override
	public Url getEncodedUrl(String url) {
		Url urlToRet = urlRepository.findByShortLink(url);
		return urlToRet;
	}

	@Override
	public void deleteShortLink(Url url) {

		urlRepository.delete(url);
	}


	
}
