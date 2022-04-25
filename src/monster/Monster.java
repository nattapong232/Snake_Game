package monster;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import base.Eatable;
import base.Food;
import base.Moveable;
import gui.GamePane;
import item.Mushroom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster extends ImageView implements Moveable{
	public static int amount = 0; // represent amount of monster that visible = true
	public static ArrayList<Monster> allMonster = new ArrayList<Monster>();
	Coordinate location;
	Image picture;

	public Monster() {
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

	public int getXLocation() {
		return location.getX();
	}

	public int getYLocation() {
		return location.getY();
	}
	
	public void initialize() {
		this.setVisible(true);
		Monster.amount += 1;
	}

	public Coordinate getLocation() {
		return location;
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
	
	public void setLocation(Coordinate c) {
		int x = c.getX();
		int y = c.getY();
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

	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
	}

	public void move() {
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
	}

	@Override
	public void randomLocation() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		this.setLocation(rand.nextInt(18) * 30, rand.nextInt(18) * 30);
	}
}

