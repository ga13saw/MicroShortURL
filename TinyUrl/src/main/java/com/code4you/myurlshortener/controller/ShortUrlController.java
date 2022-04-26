package com.code4you.myurlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.code4you.myurlshortener.model.UrlDto;
import com.code4you.myurlshortener.repository.UrlRepository;
import com.code4you.myurlshortener.services.UrlService;

import lombok.RequiredArgsConstructor;

//@CrossOrigin(origins = "${spring.kafka.bootstrap-servers}")
@CrossOrigin(origins = "localhost:9092")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ShortUrlController {
	
	@Autowired
	UrlRepository repository;
	@Autowired
	UrlService service;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//	public List<Url> findAll(){
//		return repository.findAll();
//	}
	
	@PostMapping("/url")
	@ResponseStatus(HttpStatus.CREATED)
	public void createUrl(@RequestBody UrlDto dto) {
		// TODO Auto-generated method stub
	
		kafkaTemplate.send("url", dto.toString());
		
		service.generateShortLink(dto);
	}
}
