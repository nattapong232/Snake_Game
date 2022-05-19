package food;

import base.Coordinate;
import javafx.scene.image.Image;

public class Apple extends Food{
	public Apple() {
		this.location = new Coordinate(0,0);
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
		this.setVisible(true);
	}
}