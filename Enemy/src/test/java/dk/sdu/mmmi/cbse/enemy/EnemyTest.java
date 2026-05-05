package dk.sdu.mmmi.cbse.enemy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void enemyStartsAliveWithMaxHealth() {
        Enemy enemy = new Enemy();

        assertEquals(Enemy.MAX_HEALTH, enemy.getHealth());
        assertFalse(enemy.isDead());
    }

    @Test
    void takeDamageReducesHealthUntilZero() {
        Enemy enemy = new Enemy();

        enemy.takeDamage();
        assertEquals(2, enemy.getHealth());
        assertFalse(enemy.isDead());

        enemy.takeDamage();
        assertEquals(1, enemy.getHealth());
        assertFalse(enemy.isDead());

        enemy.takeDamage();
        assertEquals(0, enemy.getHealth());
        assertTrue(enemy.isDead());
    }

    @Test
    void takeDamageDoesNotMakeHealthNegative() {
        Enemy enemy = new Enemy();

        for (int i = 0; i < Enemy.MAX_HEALTH + 2; i++) {
            enemy.takeDamage();
        }

        assertEquals(0, enemy.getHealth());
        assertTrue(enemy.isDead());
    }

    @Test
    void settersUpdateMovementAndCooldownState() {
        Enemy enemy = new Enemy();

        enemy.setMoveAngle(45.5);
        enemy.setMoveTicks(30);
        enemy.setShotCooldown(12);

        assertEquals(45.5, enemy.getMoveAngle());
        assertEquals(30, enemy.getMoveTicks());
        assertEquals(12, enemy.getShotCooldown());
    }
}
