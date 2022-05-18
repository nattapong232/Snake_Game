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
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
	
}
