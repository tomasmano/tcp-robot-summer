package psi.manotoma.robotserver.server.support.sender;

import psi.manotoma.robotserver.io.RobotResponseOutputStream;
import psi.manotoma.robotserver.robot.RobotResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RobotResponseSender implements ResponseSender<RobotResponse> {

    public static final Logger LOG = LoggerFactory.getLogger(RobotResponseSender.class);
    
    private static ResponseSender<RobotResponse> INSTANCE = new RobotResponseSender();

    public static ResponseSender<RobotResponse> getInstance() {
        return INSTANCE;
    }
    
    public void send(RobotResponse res, OutputStream os) {
        RobotResponseOutputStream rros = null;
        try {
            rros = new RobotResponseOutputStream(new BufferedOutputStream(os));
            rros.write(res);
            rros.flush();
        } catch (Exception ex) {
            LOG.error("An error occured while sending response: " + ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }
    
}
