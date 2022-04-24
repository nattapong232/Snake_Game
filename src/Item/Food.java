package item;
import java.util.Random;

import base.Coordinate;
import interfaces.Eatable;
import interfaces.Moveable;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import snake.Head;

public abstract class Food extends ImageView implements Moveable,Eatable{
	Coordinate location;
	Image picture;
	public int getXLocation() {
		return location.getX();
	}
	
	public int getYLocation() {
		return location.getY();
	}
	public Coordinate getLocation() {
		return location;
	}
	
	public abstract void setLocation(int x, int y);
	
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
	
	public abstract void initialize();
}
