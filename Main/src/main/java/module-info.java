module Main {
    requires javafx.graphics;
    requires Common;
    requires Player;
    requires spring.context;
    uses dk.sdu.mmmi.cbse.service.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.service.IGamePluginService;
    uses dk.sdu.mmmi.cbse.service.IPostEntityProcessingService;
    opens dk.sdu.mmmi.cbse.main to javafx.graphics, spring.core, spring.beans, spring.context;
}