package org.hisrc.bahnhofdirect.controller;

import org.hisrc.bahnhofdirect.model.Stop;
import org.hisrc.bahnhofdirect.service.AgencyStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StopController {

	@Autowired
	private AgencyStopService agencyStopService;

	@CrossOrigin(origins = { "*" })
	@GetMapping(value = "/stop/{lon:.+}/{lat:.+}")
	@ResponseBody
	public Stop findByLonLat(@RequestParam(value = "agencyId", defaultValue = "db") String agencyId,
			@PathVariable(value = "lon") double lon, @PathVariable(value = "lat") double lat)
			throws StopNotFoundException {
		Stop stop = agencyStopService.findNearestStopByAgencyIdAndLonLat(agencyId, lon, lat);
		if (stop != null) {
			return stop;
		} else {
			throw new StopNotFoundException(lon, lat);
		}
	}

}
