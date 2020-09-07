package com.arthur.ezshort.api.shortenedurl;

//TODO implement better hashing
public class UrlUtils {

	private UrlUtils() {
	}

	public static String encode(Long id) {
		return Long.toHexString(id);
	}

	public static Long decode(String shortUrlParam) {
		return Long.parseLong(shortUrlParam, 16);
	}

}
