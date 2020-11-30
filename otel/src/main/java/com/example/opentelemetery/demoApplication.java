package com.example.opentelemetery;

import com.example.opentelemetery.health.TemplateHealthCheck;
import com.example.opentelemetery.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class demoApplication extends Application<demoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new demoApplication().run(args);
    }

    @Override
    public String getName() {
        return "demo";
    }

    @Override
    public void initialize(final Bootstrap<demoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final demoConfiguration configuration,
                    final Environment environment) {

        // Adding Hello World Resource
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        // Adding Health Check Resource
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
