module Monitoring {
    requires javafx.controls;
    requires Common;
    requires jdk.management;

    exports dk.sdu.mmmi.cbse.Monitoring;

    provides dk.sdu.mmmi.cbse.service.IMoniteringService
            with dk.sdu.mmmi.cbse.Monitoring.FPSCounter,
                    dk.sdu.mmmi.cbse.Monitoring.CPUCounter,
                    dk.sdu.mmmi.cbse.Monitoring.MemoryCounter;
}