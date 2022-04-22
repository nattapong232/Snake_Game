package interfaces;

import base.Coordinate;

public interface Locatable {
	public int getXLocation();

	public int getYLocation();
	
	public Coordinate getLocation();

	public void setLocation(int x, int y);
}
