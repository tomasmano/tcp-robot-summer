package psi.manotoma.robotserver.exception;

import psi.manotoma.robotserver.exception.handler.RobotExceptionVisitor;
import psi.manotoma.robotserver.robot.RobotRequest;
import psi.manotoma.robotserver.robot.RobotResponse;
import java.io.OutputStream;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public abstract class RobotException extends RuntimeException {

    protected RobotRequest request;

    public RobotException() {
    }

    public RobotException(RobotRequest request) {
        this.request = request;
    }
    
    public RobotException(String message) {
        super(message);
    }

    public RobotException(String message, Throwable cause) {
        super(message, cause);
    }

    public RobotException(Throwable cause) {
        super(cause);
    }

    protected RobotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RobotRequest getRequest() {
        return request;
    }
  
    public abstract RobotResponse accept(RobotExceptionVisitor visitor, OutputStream os);
}
