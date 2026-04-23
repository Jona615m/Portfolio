package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.data.Entity;

public class Player extends Entity {
    public static final int MAX_HEALTH = 3;

    private int health = MAX_HEALTH;
    private int damageCooldown = 0;

    public int getHealth() {
        return health;
    }

    public boolean isDead() {
        return health <= 0;
    }
    public int getDamageCooldown() {
        return damageCooldown;
    }

    public void takeDamage() {
        if (health > 0) {
            health --;
            damageCooldown = 60; // 1 second cooldown
        }
    }

    public void Cooldown() {
        if (damageCooldown > 0) {
            damageCooldown--;
        }
    }

}
