package logic;

import javafx.application.Platform;

public class MovingThread extends Thread {
	public void run() {
		while (true) {
			while ((!GameLogic.getInstance().isGameEnd())) {
				// this will perform the task and wait 0.2 sec to perform the task again
				// during this perform the task other threads will not work
				// but this task is short (move,checkEat,isCrash) and it can quickly finished so
				// it not affect other threads
				if (!GameLogic.getInstance().isPause()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							GameLogic.getInstance().getControlPane().getGamePane().getSnake().requestFocus();
							GameLogic.getInstance().getControlPane().getGamePane().getSnake().move();
							GameLogic.getInstance().getControlPane().getGamePane().checkEat();
							if (GameLogic.getInstance().getControlPane().getGamePane().getSnake().isCrash()) {
								GameLogic.getInstance().setGameEnd(true);
							}
						}
					});
//					GameLogic.getInstance().getControlPane().getGamePane().getSnake().move();
//					GameLogic.getInstance().getControlPane().getGamePane().checkEat();
//					if (GameLogic.getInstance().getControlPane().getGamePane().getSnake().isCrash()) {
//						GameLogic.getInstance().setGameEnd(true);
//					}
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
