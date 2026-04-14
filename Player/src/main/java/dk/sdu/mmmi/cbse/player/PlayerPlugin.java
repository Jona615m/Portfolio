package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.World;
import dk.sdu.mmmi.cbse.service.IGamePluginService;


public class PlayerPlugin implements IGamePluginService {

    private Entity player;
    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayerShip(gameData);
        world.addEntity(player);

    }
    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        playerShip.setX(gameData.getDisplayHeight() / 2);
        playerShip.setY(gameData.getDisplayWidth() / 2);
        playerShip.setRadius(8);
        return playerShip;
    }

    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

}
