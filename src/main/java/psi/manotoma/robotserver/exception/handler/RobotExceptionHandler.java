package psi.manotoma.robotserver.exception.handler;

import psi.manotoma.robotserver.exception.RobotException;
import psi.manotoma.robotserver.robot.RobotResponse;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotExceptionHandler implements ExceptionHandler<RobotResponse, RobotException> {

    public static final Logger LOG = LoggerFactory.getLogger(RobotExceptionHandler.class);
    
    private static RobotExceptionHandler INSTANCE = new RobotExceptionHandler();
    
    private RobotExceptionVisitor visitor = new RobotExceptionVisitor();

    @Override
    public RobotResponse handle(RobotException ex, OutputStream os) {
        return ex.accept(visitor, os);
    }

    public static RobotExceptionHandler getInstance() {
        return INSTANCE;
    }
    
}
