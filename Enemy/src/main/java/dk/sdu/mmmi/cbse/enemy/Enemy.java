package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.data.Entity;

public class Enemy extends Entity {

	public static final int MAX_HEALTH = 3;

	private int health = MAX_HEALTH;

	//All being used inside the EnemyControlSystem

	private double moveAngle;
	private int moveTicks;
	private int shotCooldown;

	public double getMoveAngle() {
		return moveAngle;
	}

	public void setMoveAngle(double moveAngle) {
		this.moveAngle = moveAngle;
	}

	public int getMoveTicks() {
		return moveTicks;
	}

	public void setMoveTicks(int moveTicks) {
		this.moveTicks = moveTicks;
	}

	public int getShotCooldown() {
		return shotCooldown;
	}

	public void setShotCooldown(int shotCooldown) {
		this.shotCooldown = shotCooldown;
	}

			public int getHealth() {
				return health;
			}

			public void takeDamage() {
				if (health > 0) {
					health--;
				}
			}

			public boolean isDead() {
				return health <= 0;
			}
}
