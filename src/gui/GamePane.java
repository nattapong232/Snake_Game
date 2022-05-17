package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import base.Coordinate;
import base.MoveableObject;
import food.Apple;
import food.BadApple;
import food.Food;
import interfaces.Eatable;
import interfaces.Moveable;
import item.Battery;
import item.Item;
import item.SlowPotion;
import item.SpeedPotion;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import logic.GameLogic;
import monster.Demon;
import monster.Wall;
import snake.Body;
import snake.Head;
import snake.Snake;
import javafx.scene.input.KeyEvent;

public class GamePane extends Pane {
	private Snake snake;
	private Apple apple;
	private ArrayList<MoveableObject>[][] locationTable;
//	private MediaPlayer eatingSound;
//	private MediaPlayer collectItemSound;
	
	public GamePane() {
//		String eatingSoundFile = "eating-sound.wav";     
//		Media eatingSfx = new Media(new File(eatingSoundFile).toURI().toString());
//		eatingSound = new MediaPlayer(eatingSfx);
//		String collectItemSoundFile = "collect-item-sound.wav";
//		Media collectItemSfx = new Media(new File(collectItemSoundFile).toURI().toString());
//		collectItemSound = new MediaPlayer(collectItemSfx);
		
		snake = new Snake(60, 60);
		apple = new Apple();
		for (int i = 0; i < 3; i++) {
			BadApple b = new BadApple();
			this.getChildren().add(b);
		}
		for (int i = 0; i < 5; i++) {
			Demon mo = new Demon();
			this.getChildren().add(mo);
		}
		for (int i = 0; i < 5; i++) {
			SpeedPotion p = new SpeedPotion();
			this.getChildren().add(p);
		}
		for (int i = 0; i < 5; i++) {
			SlowPotion s = new SlowPotion();
			this.getChildren().add(s);
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if ((i == 0) || (i == 18) || (j == 0) || (j == 18)) {
					Wall w = new Wall();
					w.setLocation(i * 30, j * 30);
					w.move();
					this.getChildren().add(w);
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			Battery e = new Battery();
			this.getChildren().add(e);
		}
		
		locationTable = (ArrayList<MoveableObject>[][]) new ArrayList[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				locationTable[i][j] = new ArrayList<MoveableObject>();
			}
		}
		this.getChildren().add(snake);
		this.getChildren().add(apple);
		this.setPrefHeight(600);
		this.setPrefWidth(600);
		Image image = new Image("LevelBG.png", 600, 600, false, false);
		BackgroundSize bgSize = new BackgroundSize(600, 600, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		this.setBackground(new Background(bgImg));

		this.setOnKeyPressed(event -> {

			KeyCode k = event.getCode();
			// -----------------------------------
			// ------------input sound here------------
			// -----------------------------------
			int currentDirection = this.snake.getSnake().get(0).getDirection();
			if ((!GameLogic.getInstance().isPause()) && (!GameLogic.getInstance().isGameEnd()) && (snake.isCanChangeDirection())) {
				switch (k) {
				case A:
					if (currentDirection == 1 || currentDirection == 3) {
						snake.getSnake().get(0).setRotate(90);
						snake.getSnake().get(0).setDirection(0);
						currentDirection = 0;
					}
					break;
				case W:
					if (currentDirection == 0 || currentDirection == 2) {
						snake.getSnake().get(0).setRotate(180);
						snake.getSnake().get(0).setDirection(1);
						currentDirection = 1;
					}
					break;
				case D:
					if (currentDirection == 1 || currentDirection == 3) {
						snake.getSnake().get(0).setRotate(270);
						snake.getSnake().get(0).setDirection(2);
						currentDirection = 2;
					}
					break;
				case S:
					if (currentDirection == 0 || currentDirection == 2) {
						snake.getSnake().get(0).setRotate(0);
						snake.getSnake().get(0).setDirection(3);
						currentDirection = 3;
					}
					break;
//				case P:
//					System.out.println("Pause");
//					GameLogic.getInstance().getControlPane().getPauseButton().fire();
				}
				snake.setCanChangeDirection(false);
			}
			//to prevent bug when press two key direction and snake go back to hit itself
		});
	}

	public ArrayList<MoveableObject>[][] getLocationTable() {
		return locationTable;
	}

	public void setLocationTable(ArrayList<MoveableObject>[][] locationTable) {
		this.locationTable = locationTable;
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public Apple getApple() {
		return apple;
	}

	public void setApple(Apple apple) {
		this.apple = apple;
	}

	public void updateLocation() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				locationTable[i][j].clear();
			}
		}
		for (int i = 0; i < snake.getLength(); i++) {
			Body tempBody = snake.getSnake().get(i);
			locationTable[tempBody.getXLocation() / 30][tempBody.getYLocation() / 30].add(tempBody);
		}
		locationTable[apple.getXLocation() / 30][apple.getYLocation() / 30].add(apple);
		for (int i = 0; i < SlowPotion.getAmount(); i++) {
			SlowPotion tempSlowPotion = SlowPotion.getAllSlowPotion().get(i);
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					locationTable[tempSlowPotion.getXLocation() / 30 + j][tempSlowPotion.getYLocation() / 30 + k]
							.add(tempSlowPotion);
				}
			}
		}
		for (int i = 0; i < Demon.getAmount(); i++) {
			Demon tempMonster = Demon.getAllMonster().get(i);
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					locationTable[tempMonster.getXLocation() / 30 + j][tempMonster.getYLocation() / 30 + k]
							.add(tempMonster);
				}
			}
		}
		for (int i = 0; i < SpeedPotion.getAmount(); i++) {
			SpeedPotion tempPoison = SpeedPotion.getAllSpeedPotion().get(i);
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					locationTable[tempPoison.getXLocation() / 30 + j][tempPoison.getYLocation() / 30 + k]
							.add(tempPoison);
				}
			}
		}
		for (int i = 0; i < Wall.getAmount(); i++) {
			Wall tempWall = Wall.getAllWall().get(i);
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					locationTable[tempWall.getXLocation() / 30 + j][tempWall.getYLocation() / 30 + k].add(tempWall);
				}
			}
		}
		
		for (int i = 0; i < Battery.getAmount(); i++) {
			Battery tempEnergyPotion = Battery.getAllEnergyPotion().get(i);
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					locationTable[tempEnergyPotion.getXLocation() / 30 + j][tempEnergyPotion.getYLocation() / 30 + k].add(tempEnergyPotion);
				}
			}
		}

		for (int i = 0; i < BadApple.getAmount(); i++) {
			BadApple tempBadApple = BadApple.getAllBadApple().get(i);
			locationTable[tempBadApple.getXLocation() / 30][tempBadApple.getYLocation() / 30].add(tempBadApple);
		}
		
	}

	public void checkInteract(int x, int y) {
		// check if snake's head interact with something
		ArrayList<MoveableObject> temp = locationTable[x / 30][y / 30];
		if ((temp.size() > 1)) {
			if ((temp.get(1).isVisible())) {
				if ((temp.get(1) instanceof Eatable)) {
//					System.out.println("Eat!");
					checkEat(x / 30, y / 30);

				} else {
					snake.setCrash(true);
				}
			}
		}
	}

	// check what is eaten
	public void checkEat(int x, int y) {
		MoveableObject n = locationTable[x][y].get(1);
//		String eatingSoundFile = "eating-sound.wav";     
//		Media eatingSfx = new Media(new File(eatingSoundFile).toURI().toString());
//		MediaPlayer eatingSound = new MediaPlayer(eatingSfx);
		MediaPlayer eatingSound = GameLogic.getInstance().getEatingSound();
		MediaPlayer collectItemSound = GameLogic.getInstance().getCollectItemSound();
		if (n instanceof Food) {
			if(GameLogic.getInstance().isSfxOn()) {
				eatingSound.seek(eatingSound.getStartTime());
				eatingSound.play();
			}
			
			if (n instanceof Apple) {
				GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() + 1);
				GameLogic.getInstance().getGamePane().getSnake().getStamina().setSp(100);
				snake.getSnake().get((snake.getLength())).initialize();
				snake.updateLength();
				snake.setLength(snake.getLength());
				moveToRandomLocation((Moveable) n);
			} 
			 else if (n instanceof BadApple) {
					GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() - 1);
					snake.getSnake().get((snake.getLength())).initialize();
					snake.updateLength();
					snake.setLength(snake.getLength());
					n.setVisible(false);
				}
			
		}
		else if (n instanceof Item) {
			if(GameLogic.getInstance().isSfxOn()) {
				collectItemSound.seek(collectItemSound.getStartTime());
				collectItemSound.play();
			}
			
			if (n instanceof SlowPotion) {
				GameLogic.getInstance().setSleepTime(120);
				n.setVisible(false);
			} else if (n instanceof SpeedPotion) {
//				Deduct point, increase snake length, increase speed
				GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() - 2);
				for (int i = 0; i < 3; i++) {
					snake.getSnake().get((snake.getLength())).setVisible(true);
					snake.updateLength();
				}
				GameLogic.getInstance().setSleepTime(50);
				n.setVisible(false);
			} else if (n instanceof Battery) {
				int currentSp = GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp();
				GameLogic.getInstance().getGamePane().getSnake().getStamina().setSp(currentSp+20);
				n.setVisible(false);
			}
		}
		updateLocation();
//		if (n instanceof Apple) {
//			
//			if(GameLogic.getInstance().isSfxOn()) {
//				eatingSound.seek(eatingSound.getStartTime());
//				eatingSound.play();
//			}
//		
//			
//			GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() + 1);
//			GameLogic.getInstance().getGamePane().getSnake().getStamina().setSp(100);
//			snake.getSnake().get((snake.getLength())).initialize();
//			snake.updateLength();
//			snake.setLength(snake.getLength());
//			moveToRandomLocation((Moveable) n);
//		} else if (n instanceof SlowPotion) {
////			Slow down
//			
//			if(GameLogic.getInstance().isSfxOn()) {
//				collectItemSound.seek(collectItemSound.getStartTime());
//				collectItemSound.play();
//			}
//			
//			GameLogic.getInstance().setSleepTime(120);
//			n.setVisible(false);
//		} else if (n instanceof SpeedPotion) {
////			Deduct point, increase snake length, increase speed
//			
//			if(GameLogic.getInstance().isSfxOn()) {
//				collectItemSound.seek(collectItemSound.getStartTime());
//				collectItemSound.play();
//			}
//			
//			GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() - 2);
//			for (int i = 0; i < 3; i++) {
//				snake.getSnake().get((snake.getLength())).setVisible(true);
//				snake.updateLength();
//			}
//			GameLogic.getInstance().setSleepTime(50);
//			n.setVisible(false);
//		} else if (n instanceof Energy) {
//			
//			if(GameLogic.getInstance().isSfxOn()) {
//				collectItemSound.seek(collectItemSound.getStartTime());
//				collectItemSound.play();
//			}
//			
//			int currentSp = GameLogic.getInstance().getGamePane().getSnake().getStamina().getSp();
//			GameLogic.getInstance().getGamePane().getSnake().getStamina().setSp(currentSp+20);
//			n.setVisible(false);
//		} else if (n instanceof BadApple) {
////			Deduct point
//			
//			if(GameLogic.getInstance().isSfxOn()) {
//				eatingSound.seek(eatingSound.getStartTime());
//				eatingSound.play();
//			}
//			
//			GameLogic.getInstance().updateScore(GameLogic.getInstance().getScore() - 1);
//			snake.getSnake().get((snake.getLength())).initialize();
//			snake.updateLength();
//			snake.setLength(snake.getLength());
//			n.setVisible(false);
//		}
		
		
//		updateLocation();
	}

	public void moveToRandomLocation(Moveable m) {
		boolean canChange = false;
		int currentX = m.getXLocation();
		int currentY = m.getYLocation();

		while (!canChange) {
			canChange = true;
			m.randomLocation();
			int newX = m.getXLocation();
			int newY = m.getYLocation();
			int headX = snake.getHead().getXLocation();
			int headY = snake.getHead().getYLocation();
			updateLocation();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					try {
//						System.out.println(locationTable[(newX / 30) + i][(newY / 30) + j].size());
						if (locationTable[(newX / 30) + i][(newY / 30) + j].size() > 1 || newX <= 60 || newY <= 60 
								|| newX == headX || newY == headY ) {
							canChange = false;
						}
						else {
//							System.out.println("canChange = " + canChange);
//							System.out.println("newX = " + newX);
//							System.out.println("newY = " + newY);
						}
					}
					catch (ArrayIndexOutOfBoundsException e ){
						;
					}
				}
			}
//			System.out.println(m.getClass()+ " canChange = " + canChange);
//			System.out.println("newX = " + newX);
//			System.out.println("newY = " + newY);
		}
//		System.out.println("canChange = " + canChange);
//		System.out.println("newX = " + newX);
//		System.out.println("newY = " + newY);
		updateLocation();
		m.move();
	}
	
}
