package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.bullet.Bullet;
import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.World;
import dk.sdu.mmmi.cbse.service.IGamePluginService;

public class BulletPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }
}
