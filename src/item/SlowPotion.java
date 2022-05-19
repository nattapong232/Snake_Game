package item;

import java.util.ArrayList;
import java.util.Random;
import base.Coordinate;
import javafx.scene.image.Image;

public class SlowPotion extends Item {
	private static int amount = 0;  // represent amount of mushroom that visible = true
	private static ArrayList<SlowPotion> allSlowPotion = new ArrayList<SlowPotion>();

	public SlowPotion() {
		this.location = new Coordinate(0, 0);
		String slowPotionUrl = ClassLoader.getSystemResource("item/SlowPotion.png").toString();
		this.setPicture(new Image(slowPotionUrl));
		this.setImage(this.picture);
		this.setFitWidth(60);
		this.setFitHeight(60);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
		SlowPotion.allSlowPotion.add(this);
	}

	public void initialize() {
		this.setVisible(true);
		SlowPotion.amount += 1;
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
		SlowPotion.amount = amount;
	}

	public static ArrayList<SlowPotion> getAllSlowPotion() {
		return allSlowPotion;
	}

	public static void setAllSlowPotion(ArrayList<SlowPotion> allSlowPotion) {
		SlowPotion.allSlowPotion = allSlowPotion;
	}
}