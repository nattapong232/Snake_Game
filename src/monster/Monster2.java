package monster;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import base.MoveableObject;
import gui.GamePane;
import interfaces.Eatable;
import interfaces.Moveable;
import item.Food;
import item.SlowPotion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monster2 extends Monster {
	public static int amount = 0; // represent amount of monster that visible = true
	public static ArrayList<Monster2> allMonster2 = new ArrayList<Monster2>();
//	Coordinate location;
//	Image picture;
	MoveableObject bullet;

	public Monster2() {
		this.location = new Coordinate(0,0);
		this.setPicture(new Image("monster.png"));
		this.setImage(this.picture);
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
		allMonster2.add(this);
	}
	
	public void initialize() {
		this.setVisible(true);
		Monster1.amount += 1;
	}
	
	public void fire() {
		;
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