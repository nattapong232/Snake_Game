package monster;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import base.MoveableObject;
import etc.Bullet;
import food.Food;
import gui.GamePane;
import interfaces.Eatable;
import interfaces.Moveable;
import item.SlowPotion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Peashooter extends Monster {
	private static int amount = 0; // represent amount of monster that visible = true
	private static ArrayList<Peashooter> allPeaShooter = new ArrayList<Peashooter>();
//	Coordinate location;
//	Image picture;
	private Bullet bullet;

	public Peashooter() {
		this.setBullet(new Bullet());
		this.location = new Coordinate(0,0);
		String monster2Url = ClassLoader.getSystemResource("monster/Peashooter.png").toString();
		this.setPicture(new Image(monster2Url));
		this.setImage(this.picture);
		this.setFitWidth(60);
		this.setFitHeight(60);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);

		allPeaShooter.add(this);
	}
	
	public void initialize() {
		this.bullet.setLocation(this.getXLocation()-27, this.getYLocation()+3);
		this.bullet.move();
		this.setVisible(true);
		Peashooter.amount += 1;
		this.bullet.initialize();
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
		this.setLocation(rand.nextInt(17) * 30, rand.nextInt(17) * 30);
	}
	

	public static int getAmount() {
		return amount;
	}

	public static void setAmount(int amount) {
		Peashooter.amount = amount;
	}

	

	public static ArrayList<Peashooter> getAllPeaShooter() {
		return allPeaShooter;
	}

	public static void setAllPeaShooter(ArrayList<Peashooter> allPeaShooter) {
		Peashooter.allPeaShooter = allPeaShooter;
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