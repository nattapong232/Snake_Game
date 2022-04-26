package logic;

import gui.GamePane;
import javafx.application.Platform;

public class MovingThread extends Thread {
	public void run() {
		while (true) {
			while ((!GameLogic.getInstance().isGameEnd())) {
				if (!GameLogic.getInstance().isPause()) {
					GamePane temp = GameLogic.getInstance().getControlPane().getGamePane();
					temp.getSnake().changeLocation();
					temp.updateLocation();
					temp.checkInteract(temp.getSnake().getHead().getXLocation(),temp.getSnake().getHead().getYLocation());
					if (GameLogic.getInstance().getControlPane().getGamePane().getSnake().isCrash()) {
						System.out.println("Crash!");
						GameLogic.getInstance().setGameEnd(true);
						GameLogic.getInstance().setGameWin(false);
						GameLogic.getInstance().checkGameEnd();
					}
					else {
						temp.getSnake().move();
					}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							GameLogic.getInstance().getControlPane().getGamePane().requestFocus();
						}
					});
				}
//				System.out.println("Current Thread ID: " + Thread.currentThread().getId());
				try {
					MovingThread.sleep(GameLogic.getInstance().getSleepTime());
//					Thread.sleep(1000);
//					MovingThread.sleep(200);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			try {
				MovingThread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// check every 0.2 second if gameEnd is still true

		}
	}
}
