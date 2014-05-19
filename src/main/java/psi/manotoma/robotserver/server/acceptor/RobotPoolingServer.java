package psi.manotoma.robotserver.server.acceptor;

import psi.manotoma.robotserver.Bootstrap;
import psi.manotoma.robotserver.server.support.RobotProtocolTemplate;
import psi.manotoma.robotserver.server.support.RobotServerTask;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psi.manotoma.robotserver.server.support.RobotSetupConnectionTemplate;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotPoolingServer implements Server {

    private int port = 3999; //default value
    private int poolSize = 10; //default value
    private int backlog = 100; //default value
    private int timeout = 45000; //default value (milliseconds)
    public static final Logger LOG = LoggerFactory.getLogger(RobotPoolingServer.class);
    private ExecutorService executors;
    private ServerSocket server;

    public RobotPoolingServer() {
        init();
    }

    public void init() {
        // set properties
        LOG.info("Initializing the {}..", this.getClass().getSimpleName());
        port = Bootstrap.properties.getProperty("port") == null ? port : new Integer(Bootstrap.properties.getProperty("port"));
        LOG.info("Will use the port [{}]", port);
        backlog = Bootstrap.properties.getProperty("backlog") == null ? backlog : new Integer(Bootstrap.properties.getProperty("backlog"));
        LOG.info("Will use the backlog size [{}]", backlog);
        poolSize = Bootstrap.properties.getProperty("pool_size") == null ? poolSize : new Integer(Bootstrap.properties.getProperty("pool_size"));
        LOG.info("Will use the pool size [{}]", poolSize);
        timeout = Bootstrap.properties.getProperty("timeout") == null ? timeout : new Integer(Bootstrap.properties.getProperty("timeout"));
        LOG.info("Will use the timeout [{} ms]", timeout);
        executors = Executors.newFixedThreadPool(poolSize);

        // launch server
        LOG.info("Launching robot server..");
        try {
            LOG.info("Using port [{}]..", port);
            server = new ServerSocket(port);
//            server.setSoTimeout(port);
            LOG.info("Server bound to port [{}] succesfully..", port);
        } catch (IOException ex) {
            LOG.error("An error occured during binding server on port [{}]: {}", port, ex);
            LOG.info("Shuting down..");
            System.exit(1);
        }
    }

    public void serve() {

        while (true) {
            try {
                Socket client = null;
                LOG.debug("Waiting for the clients' requests on the address: [{}/{}]...", InetAddress.getLocalHost().getHostAddress(), server.getLocalPort());
                client = server.accept();
                LOG.debug("Accepted connection from client [{}].", client.getInetAddress().getHostAddress());

                executors.submit(new RobotServerTask(
                        new RobotProtocolTemplate(
                            client.getInputStream(),
                            client.getOutputStream()),
                        new RobotSetupConnectionTemplate(
                            client.getInputStream(),
                            client.getOutputStream()),
                        client));

            } catch (IOException e) {
                LOG.error("Fail to accept connection from client: {}", e);
            } finally {
//                client.close();
            }
        }


    }

    public void stop() {
        //Stop the executor service.
        executors.shutdownNow();
        try {
            //Stop accepting requests.
            server.close();
        } catch (IOException e) {
            LOG.error("An error occured in server shutdown: {}", e);
            e.printStackTrace();
        }
        System.exit(0);
    }
}
