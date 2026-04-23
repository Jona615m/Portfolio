module Enemy {
    requires Common;
    requires Player;

    exports dk.sdu.mmmi.cbse.enemy;

    provides dk.sdu.mmmi.cbse.service.IGamePluginService with dk.sdu.mmmi.cbse.enemy.EnemyPlugin;

    provides dk.sdu.mmmi.cbse.service.IEntityProcessingService with dk.sdu.mmmi.cbse.enemy.EnemyControlSystem;

}