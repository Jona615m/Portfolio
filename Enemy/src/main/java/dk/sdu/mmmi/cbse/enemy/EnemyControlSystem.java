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


    //
    private static final int RESPAWN_TICKS = 300; // ~5 seconds at ~60 FPS
    private static final double ENEMY_SPEED = 0.5;
    private static final int MIN_MOVE_TICKS = 20;
    private static final int MAX_MOVE_TICKS = 200;
    private static final int MIN_SHOOT_COOLDOWN = 130;
    private static final int MAX_SHOOT_COOLDOWN = 240;
    private static final double AIM_SPREAD_DEGREES = 32.0;
    private static final double SHOOT_CHANCE = 0.6;

    private int respawnTicksRemaining = -1;

    @Override
    public void process(GameData gameData, World world) {
        Entity player = null;
        int enemyCount = 0;
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

            enemyCount++;

            updateRandomMovement(enemy, gameData);
            if (player != null) {
                tryShootAtPlayer(enemy, player, gameData, world, bulletServices);
            }
        }

        handleRespawn(gameData, world, enemyCount);

    }

    private void handleRespawn(GameData gameData, World world, int enemyCount) {
        if (enemyCount > 0) {
            respawnTicksRemaining = -1;
            return;
        }

        if (respawnTicksRemaining < 0) {
            respawnTicksRemaining = RESPAWN_TICKS;
            return;
        }

        respawnTicksRemaining--;
        if (respawnTicksRemaining <= 0) {
            Entity enemy = createEnemy(null, gameData);
            if (enemy != null) {
                world.addEntity(enemy);
            }
            respawnTicksRemaining = -1;
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


        //if an enemy hits a boundary its being forced a new direction
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
        //Spread is added to make the enemy not accurate, where -AIM_SPREAD_DEGREES and AIM_SPREAD_DEGREES to the player
        double spread = ThreadLocalRandom.current().nextDouble(-AIM_SPREAD_DEGREES, AIM_SPREAD_DEGREES);
        enemy.setRotation(baseAngle + spread);

        //ThreadLocalRandom is used to make sure the enemy isn't shooting everytime but by chance
        if (ThreadLocalRandom.current().nextDouble() < SHOOT_CHANCE) {
            for (IBulletService bulletService : bulletServices) {
                Entity bullet = bulletService.createBullet(enemy, gameData);
                if (bullet != null) {
                    world.addEntity(bullet);
                }
            }
        }

        //The shot cooldown is reset after the enemy has shot, so it doesn't shoot every tick
        enemy.setShotCooldown(ThreadLocalRandom.current().nextInt(MIN_SHOOT_COOLDOWN, MAX_SHOOT_COOLDOWN + 1));
    }

    @Override
    public Entity createEnemy(Entity shooter, GameData gameData) {
        Enemy enemy = new Enemy();
        enemy.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        enemy.setX(ThreadLocalRandom.current().nextDouble(20, gameData.getDisplayWidth() - 20));
        enemy.setY(ThreadLocalRandom.current().nextDouble(20, gameData.getDisplayHeight() - 20));
        enemy.setRadius(8);
        enemy.setShotCooldown(ThreadLocalRandom.current().nextInt(MIN_SHOOT_COOLDOWN, MAX_SHOOT_COOLDOWN + 1));
        return enemy;
    }
}
