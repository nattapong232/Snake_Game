package snake;

import base.MoveableObject;

public abstract class Body extends MoveableObject{
	protected int direction;
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		if (direction < 0) {
			setDirection(3);
		} else if (direction > 3) {
			setDirection(0);
		} else {
			this.direction = direction;
		}
	}
}