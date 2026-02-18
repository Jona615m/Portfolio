package dk.sdu.mmmi.cbse.service;

import dk.sdu.mmmi.cbse.data.GameData;
import dk.sdu.mmmi.cbse.data.World;

public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
