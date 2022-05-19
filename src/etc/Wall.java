package etc;

import java.util.ArrayList;
import base.Coordinate;
import base.MoveableObject;
import javafx.scene.image.Image;

public class Wall extends MoveableObject{
	private static int amount = 0;  // represent amount of wall that visible = true
	private static ArrayList<Wall> allWall = new ArrayList<Wall>();

	public Wall() {
		this.location = new Coordinate(0,0);
		String wallUrl = ClassLoader.getSystemResource("etc/Wall.jpg").toString();
		this.setPicture(new Image(wallUrl));
		this.setImage(this.picture);
		this.setFitWidth(60);
		this.setFitHeight(60);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
		allWall.add(this);
	}

	public void initialize() {
		this.setVisible(true);
		Wall.amount += 1;
	}

	public void setLocation(int x, int y) {
		if (x >= 570) {
			this.location.setX(0);
		} else if (x < 0) {
			this.location.setX(540);
		} else {
			this.location.setX(x);
		}
		if (y >= 570) {
			this.location.setY(0);
		} else if (y < 0) {
			this.location.setY(540);
		} else {
			this.location.setY(y);
		}
	}

	public static int getAmount() {
		return amount;
	}

	public static void setAmount(int amount) {
		Wall.amount = amount;
	}

	public static ArrayList<Wall> getAllWall() {
		return allWall;
	}

	public static void setAllWall(ArrayList<Wall> allWall) {
		Wall.allWall = allWall;
	}
}