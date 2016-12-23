package org.hisrc.bahnhofdirect.dataaccess.config;

import org.hisrc.bahnhofdirect.dataccess.HaltestelleRepository;
import org.hisrc.bahnhofdirect.dataccess.impl.CsvHaltestelleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HaltestelleRepositoryConfig {

	@Bean
	public HaltestelleRepository haltestelleRepository() {
		return new CsvHaltestelleRepository();
	}

}
