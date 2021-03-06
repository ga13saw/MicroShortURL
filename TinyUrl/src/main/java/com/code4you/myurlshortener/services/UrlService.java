package com.code4you.myurlshortener.services;

import org.springframework.stereotype.Service;

import com.code4you.myurlshortener.model.Url;
import com.code4you.myurlshortener.model.UrlDto;



@Service
public interface UrlService {

	public Url generateShortLink(UrlDto urlDto);

	public Url saveShortLink(Url url);

	//public Url getEncodedUrl(String url);

	//public void deleteShortLink(Url url);
}
