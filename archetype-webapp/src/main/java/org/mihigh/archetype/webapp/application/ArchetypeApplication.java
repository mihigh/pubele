package org.mihigh.archetype.webapp.application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class ArchetypeApplication extends ResourceConfig {

    public ArchetypeApplication() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
    }
}
