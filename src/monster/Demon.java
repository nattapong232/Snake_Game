package monster;

import java.util.ArrayList;
import java.util.Random;
import base.Coordinate;
import javafx.scene.image.Image;

public class Demon extends Monster {
	private static int amount = 0; // represent amount of monster that visible = true
	private static ArrayList<Demon> allDemon = new ArrayList<Demon>();

	public Demon() {
		this.location = new Coordinate(0,0);
		String monsterUrl = ClassLoader.getSystemResource("monster/Demon.png").toString();
		this.setPicture(new Image(monsterUrl));
		this.setImage(this.picture);
		this.setFitWidth(90);
		this.setFitHeight(90);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
		allDemon.add(this);
	}
	
	public void initialize() {
		this.setVisible(true);
		Demon.amount += 1;
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
		Random rand = new Random();
		this.setLocation(rand.nextInt(17) * 30, rand.nextInt(17) * 30);
	}

	public static int getAmount() {
		return amount;
	}

	public static void setAmount(int amount) {
		Demon.amount = amount;
	}

	public static ArrayList<Demon> getAllDemon() {
		return allDemon;
	}

	public static void setAllDemon(ArrayList<Demon> allMonster) {
		Demon.allDemon = allMonster;
	}
}