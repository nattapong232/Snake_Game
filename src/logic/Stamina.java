package logic;

public class Stamina {
	private int sp;
	private boolean isStop;

	public Stamina(int staminaPoint) {
		this.sp = staminaPoint;
		this.isStop = true;
	}

	public void decrementStamina(int amount) { // Decrease the timer by specified amount (milliseconds)
		if (isStaminaDepleted()) {
			return;
		}
		sp -= amount;
	}

	public boolean isStaminaDepleted() {
		return sp <= 0;
	}

	public String toString() {
		return String.format("Stamina = %02d", sp);
	}

	public boolean isStop() {
		return isStop;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}
	
	
}
