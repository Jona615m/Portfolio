module Asteroid {
    requires Common;
    requires CommonAstroids;
    exports dk.sdu.mmmi.cbse.asteroid;

    provides dk.sdu.mmmi.cbse.service.IGamePluginService with dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
}

