package org.hisrc.bahnhofdirect.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "agency_id" })
@JsonIgnoreProperties
public class Agency {

	@JsonProperty("agency_id")
	private final String id;

	public Agency(@JsonProperty("agency_id") String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Agency [" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Agency other = (Agency) obj;
		return Objects.equals(this.id, other.id);
	}
}
