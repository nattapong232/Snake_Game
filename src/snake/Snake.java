package snake;
import java.util.ArrayList;

import base.Body;
import interfaces.Moveable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Snake extends Pane implements Moveable{
	private ArrayList<Body> snake;
	private Head head;
	private int length;
	private boolean isCrash;

	public Snake(int x,int y) {
		snake = new ArrayList<Body>();
		head = new Head(x,y);
		snake.add(head);
		this.getChildren().add(head);
		snake.add(new Tail(x,y-50));
		this.getChildren().add(snake.get(1));
		for (int i = 2;i <20 ;i++) {
			snake.add(new Tail(x,y-50*i));
			snake.get(i).setVisible(false);
			this.getChildren().add(snake.get(i));
		};
		updateLength();
	}
	
	public ArrayList<Body> getSnake() {
		return snake;
	}

	public void setSnake(ArrayList<Body> snake) {
		this.snake = snake;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void updateLength() {
		int l = 0;
		for (int i = 0 ;i < snake.size(); i++) {
			if(snake.get(i).isVisible()) {
				l++;
			}
		}
		this.length = l;
	}

	public void move() {
		int x = head.getXLocation();
		int y = head.getYLocation();;
		int headDirection = head.getDirection();
//		System.out.println("Case :" + this.direction);
		switch (headDirection) {
		case (0):
			head.setLocation(x - 30, y);
			break;
		case (1):
			head.setLocation(x, y - 30);
			break;
		case (2):
			head.setLocation(x + 30, y);
			break;
		case (3):
			head.setLocation(x, y + 30);
			break;
		}
		for (int i = 1; i < 20; i++) {
			if((head.getXLocation() == snake.get(i).getXLocation()) &&(head.getYLocation() == snake.get(i).getYLocation())
					&& (snake.get(i).isVisible()))
			{
				setCrash(true);
			}
		}
		if(!isCrash) {
			head.setTranslateX(head.getXLocation());
			head.setTranslateY(head.getYLocation());
			
			for (int i = 1; i < 20; i++) {
				Tail tail = (Tail) snake.get(i);
				int tempX = tail.getLocation().getX();
				int tempY = tail.getLocation().getY();
				tail.setLocation(x , y);
				tail.setTranslateX(x);
				tail.setTranslateY(y);
				x = tempX;
				y = tempY;
			}
		}
//		System.out.println(length);
//		System.out.println(head.getXLocation()+" "+head.getYLocation());
	}

	public boolean isCrash() {
		return isCrash;
	}

	public void setCrash(boolean isCrash) {
		this.isCrash = isCrash;
	}
	
	
}
