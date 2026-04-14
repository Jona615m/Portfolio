package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;

public interface IBulletService {
    Entity createBullet(Entity e, GameData gameData);
}
