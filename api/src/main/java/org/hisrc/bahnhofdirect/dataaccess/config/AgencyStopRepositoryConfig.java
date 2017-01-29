package org.hisrc.bahnhofdirect.dataaccess.config;

import org.hisrc.bahnhofdirect.dataccess.AgencyStopRepository;
import org.hisrc.bahnhofdirect.dataccess.impl.CsvAgencyStopRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgencyStopRepositoryConfig {

	@Bean
	public AgencyStopRepository agencyStopRepository() {
		return new CsvAgencyStopRepository();
	}

}
