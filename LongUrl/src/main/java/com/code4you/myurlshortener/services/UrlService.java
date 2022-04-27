package com.code4you.myurlshortener.services;

import org.springframework.stereotype.Service;

import com.code4you.myurlshortener.model.Url;



@Service
public interface UrlService {

	public Url getEncodedUrl(String url);

	public void deleteShortLink(Url url);
}
