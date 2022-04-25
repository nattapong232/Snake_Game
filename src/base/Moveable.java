package base;

public interface Moveable {
	public void move();
	public int getXLocation();

	public int getYLocation();
	
	public Coordinate getLocation();

	public void setLocation(int x, int y);
	
	public void randomLocation();
}
