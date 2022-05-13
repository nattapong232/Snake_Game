package base;
import java.util.ArrayList;
import java.util.Random;

import interfaces.Moveable;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import snake.Head;

public abstract class MoveableObject extends ImageView implements Moveable{
	protected Coordinate location;
	protected Image picture;

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

	public void move() {
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
	}

	@Override
	public void randomLocation() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		this.setLocation(rand.nextInt(19) * 30, rand.nextInt(19) * 30);
	}
	
	public abstract void initialize();
}