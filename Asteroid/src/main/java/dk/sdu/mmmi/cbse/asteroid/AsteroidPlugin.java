package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.World;
import dk.sdu.mmmi.cbse.service.IEntityProcessingService;
import dk.sdu.mmmi.cbse.service.IGamePluginService;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private final Random random = new Random();
    private final Entity [] asteroids = new Entity[10];

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < asteroids.length; i++) {
            Entity asteroid = createAsteroid(gameData);
            asteroids[i] = asteroid;
            world.addEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Entity();
        int size = random.nextInt(10) + 5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(random.nextInt(gameData.getDisplayWidth()));
        asteroid.setY(random.nextInt(gameData.getDisplayHeight()));
        asteroid.setRadius(size);
        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : asteroids) {
            if  (asteroid != null) {
                world.removeEntity(asteroid);
            }
        }
    }

}

