package monster;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import base.MoveableObject;
import food.Food;
import gui.GamePane;
import interfaces.Eatable;
import interfaces.Moveable;
import item.SlowPotion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster2 extends Monster {
	private static int amount = 0; // represent amount of monster that visible = true
	private static ArrayList<Monster2> allMonster2 = new ArrayList<Monster2>();
//	Coordinate location;
//	Image picture;
	private Bullet bullet;

	public Monster2() {
		this.setBullet(new Bullet());
		this.location = new Coordinate(0,0);
		String monster2Url = ClassLoader.getSystemResource("monster/Demon.png").toString();
		this.setPicture(new Image(monster2Url));
		this.setImage(this.picture);
		this.setFitWidth(60);
		this.setFitHeight(60);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);

		allMonster2.add(this);
	}
	
	public void initialize() {
		this.bullet.setLocation(this.getXLocation()-30, this.getYLocation());
		this.bullet.move();
		this.setVisible(true);
		Monster2.amount += 1;
		this.bullet.initialize();
	}
	

	public static int getAmount() {
		return amount;
	}

	public static void setAmount(int amount) {
		Monster2.amount = amount;
	}

	public static ArrayList<Monster2> getAllMonster2() {
		return allMonster2;
	}

	public static void setAllMonster2(ArrayList<Monster2> allMonster2) {
		Monster2.allMonster2 = allMonster2;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	
//	public void setLocation(int x, int y) {
//		if (x >= 600) {
//			this.location.setX(0);
//		} else if (x < 0) {
//			this.location.setX(570);
//		} else {
//			this.location.setX(x);
//		}
//		if (y >= 600) {
//			this.location.setY(0);
//		} else if (y < 0) {
//			this.location.setY(570);
//		} else {
//			this.location.setY(y);
//		}
//	}
//	
//	@Override
//	public void randomLocation() {
//		// TODO Auto-generated method stub
//		Random rand = new Random();
//		this.setLocation(rand.nextInt(18) * 30, rand.nextInt(18) * 30);
//	}
}