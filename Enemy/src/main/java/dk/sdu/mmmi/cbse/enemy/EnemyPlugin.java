package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.World;
import dk.sdu.mmmi.cbse.service.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {

    private Enemy enemy;

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
        }



    private Enemy createEnemy(GameData gameData) {
        Enemy enemy = new Enemy();
        enemy.setPolygonCoordinates(-6, -6, 6, -6, 6, 6, -6, 6);
        enemy.setX(100);
        enemy.setY(100);
        enemy.setRadius(8);
        enemy.setRotation(0);
        return enemy;
        }

    @Override
    public void stop(GameData gameData, World world) {
        if (enemy != null) {
            world.removeEntity(enemy);
        }
    }

    }


