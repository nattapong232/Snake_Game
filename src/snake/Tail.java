package snake;
import javafx.scene.image.Image;
import base.Coordinate;
public class Tail extends Body {
	
	public Tail(int x,int y) {
		this.location = new Coordinate(x,y);
		setPicture(new Image("body.jpg"));
		this.setDirection(2);
		this.setImage(this.picture);
		this.setFitWidth(30);
		this.setFitHeight(30);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}
}
