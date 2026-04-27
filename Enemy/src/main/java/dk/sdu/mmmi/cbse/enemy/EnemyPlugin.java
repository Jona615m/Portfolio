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
        enemy.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        enemy.setX((double) gameData.getDisplayHeight() / 2);
        enemy.setY((double) gameData.getDisplayWidth() / 2);
        enemy.setRadius(8);
        return enemy;
        }

    @Override
    public void stop(GameData gameData, World world) {
        if (enemy != null) {
            world.removeEntity(enemy);
        }
    }

    }


