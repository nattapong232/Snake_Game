package food;
import java.util.Random;

import base.MoveableObject;
import interfaces.Eatable;
import interfaces.Moveable;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import snake.Head;

public abstract class Food extends MoveableObject implements Eatable{
	public abstract void initialize();
}
//	Coordinate location;
//	Image picture;
//	public int getXLocation() {
//		return location.getX();
//	}
//	
//	public int getYLocation() {
//		return location.getY();
//	}
//	public Coordinate getLocation() {
//		return location;
//	}
//	
//	public void setLocation(int x, int y) {
//		if (x >= 570) {
//			this.location.setX(0);
//		} else if (x < 0) {
//			this.location.setX(540);
//		} else {
//			this.location.setX(x);
//		}
//		if (y >= 570) {
//			this.location.setY(0);
//		} else if (y < 0) {
//			this.location.setY(540);
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
//	
//	public Image getPicture() {
//		return picture;
//	}
//	public void setPicture(Image picture) {
//		this.picture = picture;
//	}
//	@Override
//	public void move() {
//		this.setTranslateX(this.getXLocation());
//		this.setTranslateY(this.getYLocation());
//	}
//	public abstract void setLocation(int x, int y);

