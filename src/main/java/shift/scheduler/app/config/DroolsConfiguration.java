package shift.scheduler.app.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sets up Drools and inserts the application's business rules into the rule engine.
 *
 * Based on https://www.baeldung.com/drools-spring-integration
 */
@Configuration
public class DroolsConfiguration {

    private static final String SHIFT_RULES = "rules/Shifts.drl";

    @Bean
    public KieContainer getKieContainer() {

        KieServices kieServices = KieServices.Factory.get();

        // Add rules to the engine
        KieFileSystem fileSystem = kieServices.newKieFileSystem();
        fileSystem.write(ResourceFactory.newClassPathResource(SHIFT_RULES));

        // Compile the rules
        KieBuilder builder = kieServices.newKieBuilder(fileSystem);
        builder.buildAll();

        KieModule module = builder.getKieModule();
        return kieServices.newKieContainer(module.getReleaseId());
    }
}
