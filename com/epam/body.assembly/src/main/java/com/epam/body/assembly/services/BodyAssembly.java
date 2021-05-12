package com.epam.body.assembly.services;

import com.epam.config.api.ConfigService;
import com.epam.config.api.model.Config;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.dosgi.common.api.IntentsProvider;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component(service = BodyAssemblyEndpoint.class, immediate = true, property = //
        { //
                "service.exported.interfaces=*", //
                "service.exported.configs=org.apache.cxf.rs", //
                "org.apache.cxf.rs.address=/api", "cxf.bus.prop.skip.default.json.provider.registration=true" } //
)
public class BodyAssembly implements IntentsProvider, BodyAssemblyEndpoint {

    private final static Logger LOGGER = LoggerFactory.getLogger(BodyAssembly.class);

    public void assemble() {
        ConfigService configService = setupConfigClient();
        LOGGER.info("Assembling body with color: " + configService.get("color").getValue());
        assembleBodyParts();
        assembleDoors();
        assembleWindows();

        Config resultConfig = new Config();
        resultConfig.setKey("assemblyFinised");
        resultConfig.setValue("true");
        configService.add(resultConfig);
    }

    private void assembleDoors() {
        // call the doors assembly service ...
    }

    private void assembleWindows() {
        // call the windows assembly service ...
    }

    private void assembleBodyParts() {
        // assemble the body parts by triggering the various body part services
    }

    private ConfigService setupConfigClient() {
        // we register Jackson JSON provider for use by the JAX-RS client so that it can use JSON for marhsalling and unmarshalling
        LinkedList providers = new LinkedList<>();
        providers.add(new JacksonJaxbJsonProvider());

        // we use the CXF JAR-RS client factory to create a client for the ConfigService
        ConfigService configService = JAXRSClientFactory.create("http://localhost:9991/cxf/api", ConfigService.class,
                providers);
        return configService;
    }

    @Override
    public List<?> getIntents() {
        return Arrays.asList(new JacksonJaxbJsonProvider());
    }
}