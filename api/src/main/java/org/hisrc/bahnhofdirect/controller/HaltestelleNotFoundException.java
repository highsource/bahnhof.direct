package org.hisrc.bahnhofdirect.controller;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Haltestelle could not be found.")
public class HaltestelleNotFoundException extends Exception {

	private static final long serialVersionUID = 917879345119499008L;

	public HaltestelleNotFoundException(double lon, double lat) {
		super(MessageFormat.format("Could not find Haltestelle with coordinates [{0}, {1}].", lon, lat));
	}

}
