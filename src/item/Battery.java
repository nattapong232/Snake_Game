package item;

import java.util.ArrayList;
import java.util.Random;
import base.Coordinate;
import javafx.scene.image.Image;

public class Battery extends Item {
	private static int amount = 0;  // represent amount of mushroom that visible = true
	private static ArrayList<Battery> allBattery = new ArrayList<Battery>();

	public Battery() {
		this.setVisible(false);
		this.location = new Coordinate(0, 0);
		String energyUrl = ClassLoader.getSystemResource("item/Battery.png").toString();
		this.setPicture(new Image(energyUrl));
		this.setImage(this.picture);
		this.setFitWidth(60);
		this.setFitHeight(60);
		this.move();
		Battery.allBattery.add(this);
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
		Random rand = new Random();
		this.setLocation(rand.nextInt(18) * 30, rand.nextInt(18) * 30);
	}
	
	public static int getAmount() {
		return amount;
	}
	
	public static void setAmount(int amount) {
		Battery.amount = amount;
	}
	
	public static ArrayList<Battery> getAllBattery() {
		return allBattery;
	}
	
	public static void setAllBattery(ArrayList<Battery> allBattery) {
		Battery.allBattery = allBattery;
	}
}