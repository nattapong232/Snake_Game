package item;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import gui.GamePane;
import interfaces.Eatable;
import interfaces.Moveable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Battery extends Item {
	private static int amount = 0;  // represent amount of mushroom that visible = true
	private static ArrayList<Battery> allEnergyPotion = new ArrayList<Battery>();
//	Coordinate location;
//	Image picture;

	public Battery() {
		this.location = new Coordinate(0, 0);
//		this.setPicture(new Image("energy.png"));
		String energyUrl = ClassLoader.getSystemResource("item/Battery.png").toString();
		this.setPicture(new Image(energyUrl));
		this.setImage(this.picture);
		this.setFitWidth(60);
		this.setFitHeight(60);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
		Battery.allEnergyPotion.add(this);
	}
	public void initialize() {
		this.setVisible(true);
		Battery.amount += 1;
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
	@Override
	public void randomLocation() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		this.setLocation(rand.nextInt(18) * 30, rand.nextInt(18) * 30);
	}
	public static int getAmount() {
		return amount;
	}
	public static void setAmount(int amount) {
		Battery.amount = amount;
	}
	public static ArrayList<Battery> getAllEnergyPotion() {
		return allEnergyPotion;
	}
	public static void setAllEnergyPotion(ArrayList<Battery> allEnergyPotion) {
		Battery.allEnergyPotion = allEnergyPotion;
	}
	
	
	
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
//
//	public Coordinate getLocation() {
//		return location;
//	}

}