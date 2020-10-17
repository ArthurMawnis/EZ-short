package com.arthur.ezshort.api.utils;

import org.springframework.util.StringUtils;

//TODO implement better hashing
public class UrlUtils {

    private UrlUtils() {
    }

    public static boolean isValid(final String longUrl) {
	if(StringUtils.isEmpty(longUrl)) return false;
	
	// TODO
	final boolean containsProtocol = longUrl.contains("http://") || longUrl.contains("https://");
	final boolean validLen = longUrl.length() < 100 && longUrl.length() > 12;
	return containsProtocol && validLen;
    }

    public static String encode(Long id) {
	return Long.toHexString(id);
    }

    public static Long decode(String shortUrlParam) {
	return Long.parseLong(shortUrlParam, 16);
    }

}
