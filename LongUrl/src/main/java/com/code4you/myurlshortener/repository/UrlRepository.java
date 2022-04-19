package com.code4you.myurlshortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.code4you.myurlshortener.model.Url;

public interface UrlRepository extends MongoRepository<Url, String> {

	Url findByShortLink(String url);

}
