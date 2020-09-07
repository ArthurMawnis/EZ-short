package com.arthur.ezshort.api.shortenedurl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String url;
	
	public ShortenedUrl toEntity() {
		final Date now = new Date();
		ShortenedUrl entity = new ShortenedUrl();
		
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(now); 
		calendar.add(Calendar.DATE, 1);
		final Date tomorrow = calendar.getTime();
		
		entity.setOriginalUrl(this.url);
		entity.setStartDate(now);
		entity.setEndDate(tomorrow);
		return entity;
	}
}
