package snake;
import interfaces.Locatable;
import javafx.scene.image.Image;
import base.Coordinate;
import base.Body;
public class Head extends Body implements Locatable{
	public static int speed = 200;
	public Head(int x,int y) {
		this.location = new Coordinate(x,y);
		this.setDirection(3);
		this.setPicture(new Image("head.png"));
		this.setImage(this.picture);
//		this.setRotate(270);
		this.setFitWidth(30);
		this.setFitHeight(30);
	}
}
