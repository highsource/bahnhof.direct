package org.hisrc.bahnhofdirect.controller;

import org.hisrc.bahnhofdirect.model.StopResult;
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
	public StopResult findByLonLat(@RequestParam(value = "agencyId", defaultValue = "db") String agencyId,
			@PathVariable(value = "lon") double lon, @PathVariable(value = "lat") double lat)
			throws StopNotFoundException {
		StopResult stopResult = agencyStopService.findNearestStopByAgencyIdAndLonLat(agencyId, lon, lat);
		if (stopResult != null) {
			return stopResult;
		} else {
			throw new StopNotFoundException(lon, lat);
		}
	}

}
