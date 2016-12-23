package org.hisrc.bahnhofdirect.service;

import org.hisrc.bahnhofdirect.dataccess.HaltestelleRepository;
import org.hisrc.bahnhofdirect.model.Haltestelle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HaltestelleService {
	
	@Autowired
	private HaltestelleRepository haltestelleRepository;
	
	public Haltestelle findByLonLat(double lon, double lat)
	{
		return haltestelleRepository.findByLaengeAndBreite(lon, lat);
	}

}
