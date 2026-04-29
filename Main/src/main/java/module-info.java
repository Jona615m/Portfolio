module Main {
    requires javafx.graphics;
    requires Common;
    requires Asteroid;
    requires Bullets;
    requires Player;
    requires Enemy;
    requires Scoring;
    requires Collision;
    requires spring.context;
    uses dk.sdu.mmmi.cbse.service.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.service.IGamePluginService;
    uses dk.sdu.mmmi.cbse.service.IPostEntityProcessingService;
    opens dk.sdu.mmmi.cbse.main;
}