package org.hisrc.bahnhofdirect.model;

public class StopEntry {

	private Stop stop;
	private double x;
	private double y;

	public StopEntry(Stop stop, double x, double y) {
		super();
		this.stop = stop;
		this.x = x;
		this.y = y;
	}

	public Stop getStop() {
		return stop;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
