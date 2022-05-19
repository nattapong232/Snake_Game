package base;

public class Stamina {
	private int sp;

	public Stamina(int staminaPoint) {
		this.sp = staminaPoint;
	}

	public void decrementStamina(int amount) { 
		if (isStaminaDepleted()) {
			return;
		}
		sp -= amount;
	}

	public boolean isStaminaDepleted() {
		return sp <= 0;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}
}