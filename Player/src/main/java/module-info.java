import dk.sdu.mmmi.cbse.player.PlayerPlugin;

module Player {
    exports dk.sdu.mmmi.cbse.player;
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.bullet.IBulletService;

   provides dk.sdu.mmmi.cbse.service.IGamePluginService with PlayerPlugin;
   provides dk.sdu.mmmi.cbse.service.IEntityProcessingService with dk.sdu.mmmi.cbse.player.PlayerControlSystem;

}