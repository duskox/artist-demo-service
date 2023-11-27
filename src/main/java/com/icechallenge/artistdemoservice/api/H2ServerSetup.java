package com.icechallenge.artistdemoservice.api;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.h2.tools.Server.createWebServer;

@Component
public class H2ServerSetup {

    Logger logger = LoggerFactory.getLogger(H2ServerSetup.class);
    private Server webServer;

    @Value("${h2-server.port}")
    Integer h2ConsolePort;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        logger.info("starting h2 console at port "+ h2ConsolePort);
        this.webServer = createWebServer("-webPort", h2ConsolePort.toString(),
                "-tcpAllowOthers").start();
        System.out.println(webServer.getURL());
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        logger.info("stopping h2 console at port "+h2ConsolePort);
        this.webServer.stop();
    }
}
