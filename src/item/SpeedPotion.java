package item;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import gui.GamePane;
import interfaces.Eatable;
import interfaces.Moveable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpeedPotion extends Item {
	private static int amount = 0;  // represent amount of poison that visible = true
	private static ArrayList<SpeedPotion> allSpeedPotion = new ArrayList<SpeedPotion>();
//	Coordinate location;
//	Image picture;

	public SpeedPotion() {
		this.location = new Coordinate(0, 0);
//		this.setPicture(new Image("speed-potion.png"));
		String speedPotionUrl = ClassLoader.getSystemResource("item/SpeedPotion.png").toString();
		this.setPicture(new Image(speedPotionUrl));
		this.setImage(this.picture);
		this.setFitWidth(90);
		this.setFitHeight(90);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
		SpeedPotion.allSpeedPotion.add(this);
	}

	public void initialize() {
		this.setVisible(true);
		SpeedPotion.amount += 1;
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

	public static int getAmount() {
		return amount;
	}

	public static void setAmount(int amount) {
		SpeedPotion.amount = amount;
	}

	public static ArrayList<SpeedPotion> getAllSpeedPotion() {
		return allSpeedPotion;
	}

	public static void setAllSpeedPotion(ArrayList<SpeedPotion> allSpeedPotion) {
		SpeedPotion.allSpeedPotion = allSpeedPotion;
	}
	
	
//	public Image getPicture() {
//		return picture;
//	}
//
//	public void setPicture(Image picture) {
//		this.picture = picture;
//	}
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
//
//	public void setLocation(Coordinate c) {
//		int x = c.getX();
//		int y = c.getY();
//		if (x >= 570) {
//			this.location.setX(0);
//		} else if (x < 0) {
//			this.location.setX(540);
//		} else {
//			this.location.setX(x);
//		}
//		if (y >= 570) {
//			this.location.setY(0);
//		} else if (y < 0) {
//			this.location.setY(540);
//		} else {
//			this.location.setY(y);
//		}
//	}
//	public void move() {
//		this.setTranslateX(this.getXLocation());
//		this.setTranslateY(this.getYLocation());
//	}
}