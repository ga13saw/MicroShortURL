package com.code4you.myurlshortener.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.code4you.myurlshortener.constants.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.code4you.myurlshortener.model.Url;
import com.code4you.myurlshortener.model.UrlErrorResponseDto;
import com.code4you.myurlshortener.repository.UrlRepository;
import com.code4you.myurlshortener.services.UrlService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LongUrlController {
	
	@Autowired
	UrlRepository repository;
	@Autowired
	UrlService service;
	
	@GetMapping("/urls")
	@ResponseStatus(HttpStatus.OK)
	public List<Url> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/urls/{shortLink}")
	@ResponseStatus(HttpStatus.OK)
	public Url findUrl(@PathVariable String shortLink){
		return service.getEncodedUrl(shortLink);
	}
	
	@GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException {

        if(StringUtils.isEmpty(shortLink))
        {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setError(Constants.INVALID_URL);
            urlErrorResponseDto.setStatus(Constants.STATUS_400);
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto,HttpStatus.OK);
        }
        Url urlToRet = service.getEncodedUrl(shortLink);
        

        if(urlToRet == null)
        {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setError(Constants.EXPIRED_URL);
            urlErrorResponseDto.setStatus(Constants.STATUS_400);
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto,HttpStatus.OK);
        }

        if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now()))
        {
            service.deleteShortLink(urlToRet);
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setError(Constants.URL_EXPIRED_CREATE_NEW);
            urlErrorResponseDto.setStatus(Constants.STATUS_200);
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto,HttpStatus.OK);
        }

        response.sendRedirect(urlToRet.getOriginalUrl());
        return null;
    }
}
