package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.World;
import dk.sdu.mmmi.cbse.service.IGamePluginService;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private final Random random = new Random();
    private final Entity [] asteroids = new Entity[3];

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < asteroids.length; i++) {
            Asteroid asteroid = createAsteroid(gameData);
            asteroids[i] = asteroid;
            world.addEntity(asteroid);
        }
    }

    private Asteroid createAsteroid(GameData gameData) {
        Asteroid asteroid = new Asteroid();
        int size = random.nextInt(10) + 5;
        asteroid.setSize(Asteroid.Size.LARGE);
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(random.nextInt(gameData.getDisplayWidth()));
        asteroid.setY(random.nextInt(gameData.getDisplayHeight()));
        asteroid.setRotation(random.nextInt(360));
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
