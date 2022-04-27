package com.code4you.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code4you.apigateway.constant.Constant;

@RestController
public class URLFallBackController {
	
	@PostMapping("/tinyUrlServiceFallBack")
    public String tinyUrlServiceFallBackMethod() {
        return Constant.URL_SERVICES_DOWN;
    }
	

	@GetMapping("/longUrlServiceFallBack")
    public String longUrlServiceFallBackMethod() {
        return Constant.URL_SERVICES_DOWN;
    }
}
