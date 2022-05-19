package etc;

import base.Coordinate;
import base.MoveableObject;
import javafx.scene.image.Image;

public class Bullet extends MoveableObject {
	public Bullet() {
		this.location = new Coordinate(0,0);
		String bulletUrl = ClassLoader.getSystemResource("etc/Bullet.png").toString();
		this.setPicture(new Image(bulletUrl));
		this.setImage(this.picture);
		this.setFitWidth(24);
		this.setFitHeight(24);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
	}
	
	@Override
	public void initialize() {
		this.setVisible(true);
	}
	
	public void setLocation(int x, int y) { //default : for 1x1 object
		if (x >= 600) {
			this.location.setX(0);
		} else {
			this.location.setX(x);
		}
		if (y >= 600) {
			this.location.setY(0);
		} else if (y < 0) {
			this.location.setY(570);
		} else {
			this.location.setY(y);
		}
	}
}