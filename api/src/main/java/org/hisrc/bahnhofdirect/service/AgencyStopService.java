package org.hisrc.bahnhofdirect.service;

import org.hisrc.bahnhofdirect.dataccess.AgencyStopRepository;
import org.hisrc.bahnhofdirect.model.StopResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyStopService {

	@Autowired
	private AgencyStopRepository agencyStopRepository;

	public StopResult findNearestStopByAgencyIdAndLonLat(String agencyId, double lon, double lat) {
		return agencyStopRepository.findNearestStopByAgencyIdAndLonLat(agencyId, lon, lat);
	}

}
