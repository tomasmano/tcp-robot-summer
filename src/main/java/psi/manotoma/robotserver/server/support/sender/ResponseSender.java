package psi.manotoma.robotserver.server.support.sender;

import psi.manotoma.robotserver.server.model.Response;
import java.io.OutputStream;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public interface ResponseSender<RSP extends Response> {
    void send(RSP res, OutputStream os);
}
