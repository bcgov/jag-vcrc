package ca.bc.gov.open;

import ca.bc.gov.open.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VCRCComparisonToolApplication {
    @Autowired private ConfigurableApplicationContext ctx;
    @Autowired private TestService testService;

    public static void main(String args[]) {
        SpringApplication.run(VCRCComparisonToolApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            testService.runCompares();
            System.exit(SpringApplication.exit(ctx, () -> 0));
        };
    }
}
