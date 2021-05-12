package com.epam.config;


import com.epam.config.api.ConfigService;
import com.epam.config.api.model.Config;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.dosgi.common.api.IntentsProvider;
import org.osgi.service.component.annotations.Component;

import java.util.Arrays;
import java.util.List;

@Component(service = ConfigService.class, immediate = true, property = //
        { //
                "service.exported.interfaces=*", //
                "service.exported.configs=org.apache.cxf.rs", //
                "org.apache.cxf.rs.address=/api", //
                "cxf.bus.prop.skip.default.json.provider.registration=true"//,
        } //
)
public class GeneralConfigService implements ConfigService, IntentsProvider {

    // the CXF intents provide a way to register additional capabilities for the OSGI service
    // in this particular case we register an intent that supplies the Jackson JSON provider
    // so that we can use JSON for marshalling and unmarshalling
    @Override
    public List<?> getIntents() {
        return Arrays.asList(new JacksonJaxbJsonProvider());
    }

    @Override
    public Config get(String key) {
        return null;
    }

    @Override
    public Config remove(String key) {
        return null;
    }

    @Override
    public Config add(Config config) {
        return null;
    }
}
/*

    Service.exported.interfaces – specifies a list of the service interfaces to be exposed remotely (the * value encompasses all interfaces implemented by the service implementation).
    Service.exported.configs – specifies how are the services going to exposed remotely, in our example we specify REST services (as provided by the CXF JAX-RS implementation).
    Org.apache.cxf.rs.address – this is the root path under which the services will be exposed remotely (in our case that is going to be localhost:9991/cxf/api).
    Cxf.bus.prop.skip.default.json.provider.registration – this property is needed in order to tell CXF not to use the default JSON provider. Rather than that a Jackson JSON provider is registered (as a CXF intent) by specifying the org.apache.cxf.dosgi.common.api.IntentsProvider interface on the service, implementation and overriding the getIntents() method.

* */