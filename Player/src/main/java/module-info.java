module Player {
    exports dk.sdu.mmmi.cbse;
    requires Common;

   provides dk.sdu.mmmi.cbse.service.IGamePluginService with dk.sdu.mmmi.cbse.PlayerPlugin;
   provides dk.sdu.mmmi.cbse.service.IEntityProcessingService with dk.sdu.mmmi.cbse.PlayerControlSystem;

}