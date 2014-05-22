package psi.manotoma.robotserver.exception.handler;

import psi.manotoma.robotserver.exception.BadSyntaxException;
import psi.manotoma.robotserver.robot.RobotMsgsFactory;
import psi.manotoma.robotserver.robot.RobotResponse;
import psi.manotoma.robotserver.robot.Status;
import psi.manotoma.robotserver.server.support.sender.RobotResponseSender;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psi.manotoma.robotserver.exception.InvalidChecksumException;
import psi.manotoma.robotserver.exception.InvalidUsernameOrPasswordException;
import psi.manotoma.robotserver.exception.TerminatedConnectionException;
import psi.manotoma.robotserver.exception.UnknownCommandException;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotExceptionVisitor {

    public static final Logger LOG = LoggerFactory.getLogger(RobotExceptionVisitor.class);

    public RobotResponse handle(BadSyntaxException ex, OutputStream os) {
        LOG.debug("Request had bad syntax, creating 501 response...");
        RobotResponse res = RobotMsgsFactory.createErrorResponse(Status._501);
        RobotResponseSender.getInstance().send(res, os);
        return res;
    }

    public RobotResponse handle(UnknownCommandException ex, OutputStream os) {
        LOG.debug("Request had unknown command [{}], creating 501 response...", ex.getMessage());
        RobotResponse res = RobotMsgsFactory.createErrorResponse(Status._501);
        RobotResponseSender.getInstance().send(res, os);
        return res;
    }

    public RobotResponse handle(InvalidUsernameOrPasswordException ex, OutputStream os) {
        LOG.debug("{}, creating 500 response...", ex.getMessage());
        RobotResponse res = RobotMsgsFactory.createErrorResponse(Status._500);
        RobotResponseSender.getInstance().send(res, os);
        return res;
    }

    public RobotResponse handle(InvalidChecksumException ex, OutputStream os) {
        LOG.debug("{}, creating 300 response...", ex.getMessage());
        RobotResponse res = RobotMsgsFactory.createErrorResponse(Status._300);
        RobotResponseSender.getInstance().send(res, os);
        return res;
    }

    public RobotResponse handle(TerminatedConnectionException ex, OutputStream os) {
        LOG.debug("{}, creating 502 response...", ex.getMessage());
        RobotResponse res = RobotMsgsFactory.createErrorResponse(Status._502);
        // do not send anything
        return res;
    }

}
