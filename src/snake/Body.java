package snake;
import base.Coordinate;
import base.MoveableObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Body extends MoveableObject{
//	protected Image picture;
//	protected Coordinate location;
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
	
	@Override
	public abstract void initialize();
	
//	public Image getPicture() {
//	return picture;
//}
//
//public void setPicture(Image picture) {
//	this.picture = picture;
//}
//
//public int getXLocation() {
//	return location.getX();
//}
//
//public int getYLocation() {
//	return location.getY();
//}
//
//public Coordinate getLocation() {
//	return location;
//}
//
//public void setLocation(int x, int y) {
//	if (x >= 600) {
//		this.location.setX(0);
//	} else if (x < 0) {
//		this.location.setX(570);
//	} else {
//		this.location.setX(x);
//	}
//	if (y >= 600) {
//		this.location.setY(0);
//	} else if (y < 0) {
//		this.location.setY(570);
//	} else {
//		this.location.setY(y);
//	}
//}

}
