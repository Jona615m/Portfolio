package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.service.IEntityProcessingService;
import dk.sdu.mmmi.cbse.service.IGamePluginService;
import dk.sdu.mmmi.cbse.service.IPostEntityProcessingService;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {

    @Bean
    public Game game(List<IGamePluginService> gamePluginServices,
                     List<IEntityProcessingService> entityProcessingServices,
                     List<IPostEntityProcessingService> postEntityProcessingServices) {
        return new Game(gamePluginServices, entityProcessingServices, postEntityProcessingServices);
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return loadServices(IGamePluginService.class);
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServices() {
        return loadServices(IEntityProcessingService.class);
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return loadServices(IPostEntityProcessingService.class);
    }

    private static <T> List<T> loadServices(Class<T> serviceType) {
        List<T> services = new ArrayList<>();
        ServiceLoader.load(serviceType).forEach(services::add);
        return services;
    }
}

