package org.hisrc.bahnhofdirect.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="station")
public class Station {

	@XmlAttribute
	private String name;
	@XmlAttribute
	private int eva;
	@XmlAttribute
	private String ds100;
	@XmlAttribute
	private String meta;
	
	public String getDs100() {
		return ds100;
	}
	
	public int getEva() {
		return eva;
	}
	
	@Override
	public String toString() {
		return "Station [" + name + " (" + eva + "/" + ds100 + (meta == null? "" : meta) + ")]";
	}	
}
