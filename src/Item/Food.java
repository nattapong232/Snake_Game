package Item;
import java.util.Random;

import base.Coordinate;
import interfaces.Locatable;
import interfaces.Moveable;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import snake.Head;

public class Food extends ImageView implements Locatable,Moveable{
	Coordinate location;
	Image picture;
	public Food() {
		this.location = new Coordinate(330,450);
		this.setPicture(new Image("apple.png"));
		this.setImage(this.picture);
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
	}
	public int getXLocation() {
		return location.getX();
	}
	
	public int getYLocation() {
		return location.getY();
	}
	public Coordinate getLocation() {
		return location;
	}
	public void setLocation(int x, int y) {
		if (x >= 600) {
			this.location.setX(0);
		} else if (x < 0) {
			this.location.setX(570);
		} else {
			this.location.setX(x);
		}
		if (y >= 600) {
			this.location.setY(0);
		} else if (y < 0) {
			this.location.setY(570);
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
	@Override
	public void move() {
		Random rand = new Random();
		this.setLocation(rand.nextInt(19)*30, rand.nextInt(19)*30);
	}
	
	public void initializeFood() { // initialize visibility, location, direction
		this.setLocation(330, 450);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
	}
}
