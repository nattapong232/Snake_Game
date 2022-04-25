package item;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import base.Eatable;
import base.Food;
import base.Moveable;
import gui.GamePane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Poison extends Food {
	public static int amount = 0;  // represent amount of poison that visible = true
	public static ArrayList<Poison> allPoison = new ArrayList<Poison>();
	Coordinate location;
	Image picture;

	public Poison() {
		this.location = new Coordinate(0, 0);
		this.setPicture(new Image("poison mushroom.png"));
		this.setImage(this.picture);
		this.setFitWidth(90);
		this.setFitHeight(90);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
		Poison.allPoison.add(this);
	}

	public int getXLocation() {
		return location.getX();
	}

	public int getYLocation() {
		return location.getY();
	}
	
	public void initialize() {
		this.setVisible(true);
		Poison.amount += 1;
	}

	public Coordinate getLocation() {
		return location;
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