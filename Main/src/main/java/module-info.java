module Main {
    requires javafx.graphics;
    requires Common;
    requires Player;
    uses dk.sdu.mmmi.cbse.service.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.service.IGamePluginService;
    opens dk.sdu.mmmi.cbse.main to javafx.graphics;
}