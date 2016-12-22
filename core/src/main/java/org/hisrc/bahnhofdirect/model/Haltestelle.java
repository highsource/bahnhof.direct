package org.hisrc.bahnhofdirect.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "EVA_NR", "DS100", "NAME", "VERKEHR", "LAENGE", "BREITE" })
public class Haltestelle {

	@JsonProperty("EVA_NR")
	private final int evaNr;
	@JsonProperty("DS100")
	private final String ds100;
	@JsonProperty("NAME")
	private final String name;
	@JsonProperty("VERKEHR")
	private final String verkehr;
	@JsonProperty("LAENGE")
	private final double laenge;
	@JsonProperty("BREITE")
	private final double breite;
	@JsonProperty("IGNORED1")
	private final String ignored;

	@JsonCreator
	public Haltestelle(@JsonProperty("EVA_NR") int evaNr, @JsonProperty("DS100") String ds100,
			@JsonProperty("NAME") String name, @JsonProperty("VERKEHR") String verkehr,
			@JsonProperty("LAENGE") double laenge, @JsonProperty("BREITE") double breite,
			@JsonProperty("IGNORED1") String ignored) {
		super();
		this.evaNr = evaNr;
		this.ds100 = ds100;
		this.name = name;
		this.verkehr = verkehr;
		this.laenge = laenge;
		this.breite = breite;
		this.ignored = ignored;
	}

	public int getEvaNr() {
		return evaNr;
	}

	public String getDs100() {
		return ds100;
	}

	public String getName() {
		return name;
	}

	public String getVerkehr() {
		return verkehr;
	}

	public double getLaenge() {
		return laenge;
	}

	public double getBreite() {
		return breite;
	}

	@Override
	public String toString() {
		return "Haltestelle [" + name + " (" + evaNr + "/" + ds100 + " " + laenge + ", " + breite + ")]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(evaNr, ds100, name, verkehr, laenge, breite, ignored);
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
		Haltestelle other = (Haltestelle) obj;
		return Objects.equals(this.evaNr, other.evaNr) && Objects.equals(this.ds100, other.ds100)
				&& Objects.equals(this.name, other.name) && Objects.equals(this.verkehr, other.verkehr)
				&& Objects.equals(this.laenge, other.laenge) && Objects.equals(this.breite, other.breite)
				&& Objects.equals(this.ignored, other.ignored);
	}

}
