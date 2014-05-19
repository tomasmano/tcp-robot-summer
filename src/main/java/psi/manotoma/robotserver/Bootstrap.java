package psi.manotoma.robotserver;

import psi.manotoma.robotserver.server.acceptor.RobotPoolingServer;
import psi.manotoma.robotserver.server.acceptor.Server;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstrap for server. Initializes the network facility along with the current
 * configuration.
 *
 * @author Tomas Mano
 */
public class Bootstrap {

    public static Properties properties = new Properties();
    public static final String CONFIG_PROPERTIES_LOCATION = "server-config.properties";
    public static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    public static void loadProperties() {
        // load properties
        try {
            LOG.info("****************************************************************************************");
            properties.load(Bootstrap.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES_LOCATION));
            LOG.info("Using properties: {}", properties.entrySet());
        } catch (IOException ex) {
            LOG.error("An error occured during loading properties: {}", ex);
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        loadProperties();

        // create server instance
        Server server = null;
        server = new RobotPoolingServer();
        LOG.info("****************************************************************************************");
        server.serve();
    }
}
