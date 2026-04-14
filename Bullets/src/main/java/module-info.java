module Bullets {
    requires Common;
    requires CommonBullet;
    provides dk.sdu.mmmi.cbse.service.IGamePluginService with dk.sdu.mmmi.cbse.BulletPlugin;
    provides dk.sdu.mmmi.cbse.bullet.IBulletService with dk.sdu.mmmi.cbse.BulletSystem;
    provides dk.sdu.mmmi.cbse.service.IEntityProcessingService with dk.sdu.mmmi.cbse.BulletSystem;

}