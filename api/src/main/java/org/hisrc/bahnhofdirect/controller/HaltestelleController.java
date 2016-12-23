package org.hisrc.bahnhofdirect.controller;

import org.hisrc.bahnhofdirect.model.Haltestelle;
import org.hisrc.bahnhofdirect.service.HaltestelleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HaltestelleController {

	@Autowired
	private HaltestelleService haltestelleService;

	@CrossOrigin(origins = { "*" })
	@GetMapping(value = "/haltestelle/{lon:.+}/{lat:.+}")
	@ResponseBody
	public Haltestelle findByLonLat(@PathVariable(value = "lon") double lon, @PathVariable(value = "lat") double lat)
			throws HaltestelleNotFoundException {
		Haltestelle haltestelle = haltestelleService.findByLonLat(lon, lat);
		if (haltestelle != null) {
			return haltestelle;
		} else {
			throw new HaltestelleNotFoundException(lon, lat);
		}
	}

}
