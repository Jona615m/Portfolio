package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.bullet.Bullet;
import dk.sdu.mmmi.cbse.bullet.IBulletService;
import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.World;
import dk.sdu.mmmi.cbse.service.IEntityProcessingService;
import java.util.ArrayList;
import java.util.List;

public class BulletSystem implements IBulletService, IEntityProcessingService {
    @Override
    public Entity createBullet(Entity e, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setPolygonCoordinates(1, 1, -1, 1, -1, -1, 1, -1);
        bullet.setRadius(1);
        bullet.setRotation(e.getRotation());
        bullet.setShooterId(e.getID());

        double changeX = Math.cos(Math.toRadians(e.getRotation()));
        double changeY = Math.sin(Math.toRadians(e.getRotation()));
        bullet.setX(e.getX() + changeX * 12);
        bullet.setY(e.getY() + changeY * 12);
        return bullet;
    }

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> bulletsToRemove = new ArrayList<>();
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));

            bullet.setX(bullet.getX() + changeX * 6);
            bullet.setY(bullet.getY() + changeY * 6);

            if (bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth()
                    || bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()) {
                bulletsToRemove.add(bullet);
            }
        }
        for (Entity bullet : bulletsToRemove) {
            world.removeEntity(bullet);
        }
    }

    @Override
    public Entity createEnemy(Entity shooter, GameData gameData) {
        return createBullet(shooter, gameData);
    }
}
