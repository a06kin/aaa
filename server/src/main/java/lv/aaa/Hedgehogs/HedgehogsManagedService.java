package lv.aaa.Hedgehogs;

import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ManagedService(path="/info")
public class HedgehogsManagedService {

    private final Logger logger = LoggerFactory.getLogger(HedgehogsManagedService.class);

    @Get
    public void onOpen(final AtmosphereResource r) {
        r.addEventListener(new AtmosphereResourceEventListenerAdapter() {
            @Override
            public void onSuspend(AtmosphereResourceEvent event) {
                logger.info("User {} connected.", r.uuid());
            }

            @Override
            public void onDisconnect(AtmosphereResourceEvent event) {
                if (event.isCancelled()) {
                    logger.info("User {} unexpectedly disconnected", r.uuid());
                } else if (event.isClosedByClient()) {
                    logger.info("User {} closed the connection", r.uuid());
                }
            }
        });
    }

    @Message
    public String onMessage(String message) throws IOException {
        logger.info("Message:" + message);
        return "{test}";
    }

}
