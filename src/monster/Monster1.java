package monster;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import gui.GamePane;
import interfaces.Eatable;
import interfaces.Moveable;
import item.Food;
import item.SlowPotion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster1 extends Monster {
	public static int amount = 0; // represent amount of monster that visible = true
	public static ArrayList<Monster1> allMonster = new ArrayList<Monster1>();
//	Coordinate location;
//	Image picture;

	public Monster1() {
		this.location = new Coordinate(0,0);
		this.setPicture(new Image("monster.png"));
		this.setImage(this.picture);
		this.setFitWidth(90);
		this.setFitHeight(90);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
		allMonster.add(this);
	}
	
	public void initialize() {
		this.setVisible(true);
		Monster1.amount += 1;
	}

	public void setLocation(int x, int y) {
		if (x >= 540) {
			this.location.setX(0);
		} else if (x < 0) {
			this.location.setX(510);
		} else {
			this.location.setX(x);
		}
		if (y >= 540) {
			this.location.setY(0);
		} else if (y < 0) {
			this.location.setY(510);
		} else {
			this.location.setY(y);
		}
	}
	
	@Override
	public void randomLocation() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		this.setLocation(rand.nextInt(17) * 30, rand.nextInt(17) * 30);
	}
	
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
}

