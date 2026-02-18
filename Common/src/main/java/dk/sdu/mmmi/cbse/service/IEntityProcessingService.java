package dk.sdu.mmmi.cbse.service;

import dk.sdu.mmmi.cbse.data.Entity;
import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.World;

public interface IEntityProcessingService {

    void process(GameData gameData, World world);
    Entity createEnemy(Entity shooter, GameData gameData);
}
