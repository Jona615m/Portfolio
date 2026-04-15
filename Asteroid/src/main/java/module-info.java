module Asteroid {
    requires Common;
    requires CommonAstroids;
    exports dk.sdu.mmmi.cbse.asteroid;

    provides dk.sdu.mmmi.cbse.service.IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
    provides dk.sdu.mmmi.cbse.service.IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid.AsteroidProcessor;
    provides dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitter with dk.sdu.mmmi.cbse.asteroid.AsteroidSplitter;
}
