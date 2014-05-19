package psi.manotoma.robotserver.exception;

import psi.manotoma.robotserver.exception.handler.RobotExceptionVisitor;
import psi.manotoma.robotserver.robot.RobotRequest;
import psi.manotoma.robotserver.robot.RobotResponse;
import java.io.OutputStream;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class BadSyntaxException extends RobotException {
    
    public BadSyntaxException() {
    }

    public BadSyntaxException(RobotRequest request) {
        super(request);
    }

    public BadSyntaxException(String message) {
        super(message);
    }

    public BadSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadSyntaxException(Throwable cause) {
        super(cause);
    }

    public BadSyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public RobotResponse accept(RobotExceptionVisitor visitor, OutputStream os) {
        return visitor.handle(this, os);
    }
    
}
