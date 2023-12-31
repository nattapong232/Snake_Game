package snake;

import javafx.scene.image.Image;
import base.Coordinate;

public class Head extends Body{
	public Head(int x,int y) {
		this.location = new Coordinate(x,y);
		this.setDirection(3);
		String headUrl = ClassLoader.getSystemResource("head.png").toString();
		this.setPicture(new Image(headUrl));
		this.setImage(this.picture);
		this.setFitWidth(30);
		this.setFitHeight(30);
	}
	
	@Override
	public void initialize() {
		this.setRotate(this.getRotate()*0);
		this.setLocation(60, 60);
		this.move();
		this.setVisible(true);
		this.setDirection(3);
		this.setTranslateX(this.getXLocation());
		this.setTranslateY(this.getYLocation());
	}
}