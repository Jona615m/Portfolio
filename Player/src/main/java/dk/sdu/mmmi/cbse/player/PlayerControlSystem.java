package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.bullet.IBulletService;
import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.GameKeys;
import dk.sdu.mmmi.cbse.data.World;
import dk.sdu.mmmi.cbse.service.IEntityProcessingService;
import java.util.ServiceLoader;

public class PlayerControlSystem implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 5);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 5);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }

            if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
                for (IBulletService bulletService : ServiceLoader.load(IBulletService.class)) {
                    Entity bullet = bulletService.createBullet(player, gameData);
                    if (bullet != null) {
                        world.addEntity(bullet);
                    }
                }
            }

        if (player.getX() < 0) {
            player.setX(1);
        }

        if (player.getX() > gameData.getDisplayWidth()) {
            player.setX(gameData.getDisplayWidth()-1);
        }

        if (player.getY() < 0) {
            player.setY(1);
        }

        if (player.getY() > gameData.getDisplayHeight()) {
            player.setY(gameData.getDisplayHeight()-1);
        }


        }
    }

    @Override
    public Entity createEnemy(Entity shooter, GameData gameData) {
        return null;
    }

}
