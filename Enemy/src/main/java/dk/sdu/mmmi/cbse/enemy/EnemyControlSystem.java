package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.World;
import dk.sdu.mmmi.cbse.player.Player;
import dk.sdu.mmmi.cbse.bullet.IBulletService;
import dk.sdu.mmmi.cbse.service.IEntityProcessingService;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyControlSystem implements IEntityProcessingService {

    private static final double ENEMY_SPEED = 0.5;
    private static final int MIN_MOVE_TICKS = 20;
    private static final int MAX_MOVE_TICKS = 200;
    private static final int MIN_SHOOT_COOLDOWN = 130;
    private static final int MAX_SHOOT_COOLDOWN = 240;
    private static final double AIM_SPREAD_DEGREES = 32.0;
    private static final double SHOOT_CHANCE = 0.6;

    @Override
    public void process(GameData gameData, World world) {
        Entity player = null;
        List<IBulletService> bulletServices = new ArrayList<>();
        ServiceLoader.load(IBulletService.class).forEach(bulletServices::add);

        for (Entity e : world.getEntities()) {
            if (e instanceof Player) {
                player = e;
                break;
            }
        }

        for (Entity e : world.getEntities()) {
            if (!(e instanceof Enemy enemy)) {
                continue;
            }

            updateRandomMovement(enemy, gameData);
            if (player != null) {
                tryShootAtPlayer(enemy, player, gameData, world, bulletServices);
            }
        }

    }

    private void updateRandomMovement(Enemy enemy, GameData gameData) {
        if (enemy.getMoveTicks() <= 0) {
            enemy.setMoveAngle(ThreadLocalRandom.current().nextDouble(0, 360));
            enemy.setMoveTicks(ThreadLocalRandom.current().nextInt(MIN_MOVE_TICKS, MAX_MOVE_TICKS + 1));
        }

        double changeX = Math.cos(Math.toRadians(enemy.getMoveAngle())) * ENEMY_SPEED;
        double changeY = Math.sin(Math.toRadians(enemy.getMoveAngle())) * ENEMY_SPEED;
        enemy.setX(enemy.getX() + changeX);
        enemy.setY(enemy.getY() + changeY);
        enemy.setMoveTicks(enemy.getMoveTicks() - 1);

        // Keep enemies on screen and force a new direction when they hit a boundary.
        boolean hitBoundary = false;
        if (enemy.getX() < 1) {
            enemy.setX(1);
            hitBoundary = true;
        } else if (enemy.getX() > gameData.getDisplayWidth() - 1) {
            enemy.setX(gameData.getDisplayWidth() - 1);
            hitBoundary = true;
        }

        if (enemy.getY() < 1) {
            enemy.setY(1);
            hitBoundary = true;
        } else if (enemy.getY() > gameData.getDisplayHeight() - 1) {
            enemy.setY(gameData.getDisplayHeight() - 1);
            hitBoundary = true;
        }

        if (hitBoundary) {
            enemy.setMoveTicks(0);
        }
    }

    private void tryShootAtPlayer(Enemy enemy, Entity player, GameData gameData, World world,
                                  List<IBulletService> bulletServices) {
        if (enemy.getShotCooldown() > 0) {
            enemy.setShotCooldown(enemy.getShotCooldown() - 1);
            return;
        }

        double dx = player.getX() - enemy.getX();
        double dy = player.getY() - enemy.getY();
        double baseAngle = Math.toDegrees(Math.atan2(dy, dx));
        double spread = ThreadLocalRandom.current().nextDouble(-AIM_SPREAD_DEGREES, AIM_SPREAD_DEGREES);
        enemy.setRotation(baseAngle + spread);

        if (ThreadLocalRandom.current().nextDouble() < SHOOT_CHANCE) {
            for (IBulletService bulletService : bulletServices) {
                Entity bullet = bulletService.createBullet(enemy, gameData);
                if (bullet != null) {
                    world.addEntity(bullet);
                }
            }
        }

        enemy.setShotCooldown(ThreadLocalRandom.current().nextInt(MIN_SHOOT_COOLDOWN, MAX_SHOOT_COOLDOWN + 1));
    }

    @Override
    public Entity createEnemy(Entity shooter, GameData gameData) {
        return null;
    }
}
