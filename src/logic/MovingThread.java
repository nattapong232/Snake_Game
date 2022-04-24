package logic;

import javafx.application.Platform;

public class MovingThread extends Thread {
	public void run() {
		while (true) {
			while ((!GameLogic.getInstance().isGameEnd())) {
				if (!GameLogic.getInstance().isPause()) {
					GameLogic.getInstance().getControlPane().getGamePane().getSnake().changeLocation();
					GameLogic.getInstance().getControlPane().getGamePane().updateLocation();
					GameLogic.getInstance().getControlPane().getGamePane().checkInteract();
					if (GameLogic.getInstance().getControlPane().getGamePane().getSnake().isCrash()) {
						System.out.println("Crash!");
						GameLogic.getInstance().setGameEnd(true);
						GameLogic.getInstance().setGameWin(false);
						GameLogic.getInstance().checkGameEnd();
					}
					else {
						GameLogic.getInstance().getControlPane().getGamePane().getSnake().move();
						System.out.println(GameLogic.getInstance().getControlPane().getGamePane().getSnake().getXLocation());
						System.out.println(GameLogic.getInstance().getControlPane().getGamePane().getSnake().getYLocation());
					}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							GameLogic.getInstance().getControlPane().getGamePane().requestFocus();
						}
					});				
				}
				System.out.println("Current Thread ID: " + Thread.currentThread().getId());
				try {
					MovingThread.sleep(GameLogic.getInstance().getFramerate());
				} catch (Exception ex) {
				}
			}
			try {
				this.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// check every 0.2 second if gameEnd is still true

		}
	}
}
