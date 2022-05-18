package monster;

import java.util.ArrayList;

import base.Coordinate;
import base.MoveableObject;
import javafx.scene.image.Image;

public class Bullet extends MoveableObject {
//	private static int amount = 0; // represent amount of monster that visible = true
//	private static ArrayList<Demon> allBullet = new ArrayList<Demon>();
	
	public Bullet() {
		this.location = new Coordinate(0,0);
		String bulletUrl = ClassLoader.getSystemResource("apple.png").toString();
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
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
	
	public void setLocation(int x, int y) { //default : for 1x1 object
		if (x >= 600) {
			this.location.setX(0);
		} else if (x < 0) {
			this.setVisible(false);
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
