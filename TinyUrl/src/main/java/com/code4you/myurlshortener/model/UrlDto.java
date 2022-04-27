package com.code4you.myurlshortener.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UrlDto {
	
	private String url;
	private String expirationDate; // optional 
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getExpirationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "UrlDto [url=" + url + ", expirationDate=" + expirationDate + "]";
	}

	
	
	
}
