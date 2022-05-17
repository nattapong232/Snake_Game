package monster;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import base.MoveableObject;
import gui.GamePane;
import interfaces.Eatable;
import interfaces.Moveable;
import item.Food;
import item.SlowPotion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends MoveableObject{
	public static int amount = 0;  // represent amount of wall that visible = true
	public static ArrayList<Wall> allWall = new ArrayList<Wall>();
//	Coordinate location;
//	Image picture;

	public Wall() {
		this.location = new Coordinate(0,0);
//		this.setPicture(new Image("wall.jpg"));
		String wallUrl = ClassLoader.getSystemResource("wall.jpg").toString();
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
	
//	public int getXLocation() {
//		return location.getX();
//	}
//
//	public int getYLocation() {
//		return location.getY();
//	}
//
//	public Coordinate getLocation() {
//		return location;
//	}
//	public void setLocation(Coordinate c) {
//		int x = c.getX();
//		int y = c.getY();
//		if (x >= 540) {
//			this.location.setX(0);
//		} else if (x < 0) {
//			this.location.setX(510);
//		} else {
//			this.location.setX(x);
//		}
//		if (y >= 540) {
//			this.location.setY(0);
//		} else if (y < 0) {
//			this.location.setY(510);
//		} else {
//			this.location.setY(y);
//		}
//	}
//
//	public Image getPicture() {
//		return picture;
//	}
//
//	public void setPicture(Image picture) {
//		this.picture = picture;
//	}
//
//	public void move() {
//		this.setTranslateX(this.getXLocation());
//		this.setTranslateY(this.getYLocation());
//	}
//
//	
}

