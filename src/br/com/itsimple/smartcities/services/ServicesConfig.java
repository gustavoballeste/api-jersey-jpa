package br.com.itsimple.smartcities.services;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class ServicesConfig extends ResourceConfig {

    public ServicesConfig() {
        super(ClienteService.class);
        packages("br.com.itsimple.smartcities.services");
        register(RolesAllowedDynamicFeature.class);
    }
}
