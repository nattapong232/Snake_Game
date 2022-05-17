package food;

import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import gui.GamePane;
import interfaces.Eatable;
import interfaces.Moveable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BadApple extends Food {
	private static int amount = 0;  // represent amount of mushroom that visible = true
	private static ArrayList<BadApple> allBadApple = new ArrayList<BadApple>();
//	Coordinate location;
//	Image picture;

	public BadApple() {
		this.location = new Coordinate(0, 0);
//		this.setPicture(new Image("bad-apple.png"));
		String badAppleUrl = ClassLoader.getSystemResource("food/BadApple.png").toString();
		this.setPicture(new Image(badAppleUrl));
		this.setImage(this.picture);
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
		BadApple.allBadApple.add(this);
	}
	public void initialize() {
		this.setVisible(true);
		BadApple.amount += 1;
	}
	public static int getAmount() {
		return amount;
	}
	public static void setAmount(int amount) {
		BadApple.amount = amount;
	}
	public static ArrayList<BadApple> getAllBadApple() {
		return allBadApple;
	}
	public static void setAllBadApple(ArrayList<BadApple> allBadApple) {
		BadApple.allBadApple = allBadApple;
	}
	
	
}
