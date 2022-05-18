package food;
import java.util.Random;

import base.Coordinate;
import gui.GamePane;
import interfaces.Eatable;
import interfaces.Moveable;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import snake.Head;

public class Apple extends Food{
//	Coordinate location;
//	Image picture;
	public Apple() {
		this.location = new Coordinate(0,0);
//		this.setPicture(new Image("apple.png"));
		String appleUrl = ClassLoader.getSystemResource("food/Apple.png").toString();
		this.setPicture(new Image(appleUrl));
		this.setImage(this.picture);
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
		this.setVisible(false);
	}
	
	public void initialize() {
//		randomLocation();
//		move();
		this.setVisible(true);
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
//		this.setLocation(rand.nextInt(19)*30, rand.nextInt(19)*30);
//	}

//	public void setLocation(Coordinate c) {
//		int x = c.getX();
//		int y = c.getY();
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
//	public Coordinate getLocation() {
//	return location;
//}
//
//public int getXLocation() {
//	return location.getX();
//}
//
//public int getYLocation() {
//	return location.getY();
//}
//	public Image getPicture() {
//		return picture;
//	}
//	public void setPicture(Image picture) {
//		this.picture = picture;
//	}
//	
//	public void move() {
//		this.setTranslateX(this.getXLocation());
//		this.setTranslateY(this.getYLocation());
//	}

}