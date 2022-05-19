package monster;

import java.util.ArrayList;
import java.util.Random;
import base.Coordinate;
import etc.Bullet;
import javafx.scene.image.Image;

public class Peashooter extends Monster {
	private static int amount = 0; // represent amount of monster that visible = true
	private static ArrayList<Peashooter> allPeaShooter = new ArrayList<Peashooter>();
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
}