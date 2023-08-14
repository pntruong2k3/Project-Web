package com.project.ecommerc.mart247.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {
	@Value("${server.port}")
	private String port;
	@Value("${server.address}")
	private String address;
	public String getURL() {
		return address+":"+port;
	}
}
