package org.hisrc.bahnhofdirect.dataccess;

import java.util.List;

import org.hisrc.bahnhofdirect.model.Agency;
import org.hisrc.bahnhofdirect.model.AgencyStopResults;
import org.hisrc.bahnhofdirect.model.StopResult;

public interface AgencyStopRepository {

	public Agency findAgencyById(String agencyId);

	public StopResult findNearestStopByAgencyIdAndLonLat(String agencyId, double lon, double lat);

	public List<AgencyStopResults> findNearestStopByAgencyIdAndLonLat(List<String> agencyIds, double lon, double lat,
			int maxCount, double maxDistance);

}
