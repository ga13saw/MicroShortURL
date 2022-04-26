package com.code4you.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLFallBackController {
	
	@PostMapping("/tinyUrlServiceFallBack")
    public String tinyUrlServiceFallBackMethod() {
        return "Url Service is taking longer than Expected." +
                " Please try again later";
    }
	

	@GetMapping("/longUrlServiceFallBack")
    public String longUrlServiceFallBackMethod() {
        return "Url Service is taking longer than Expected." +
                " Please try again later";
    }
}
