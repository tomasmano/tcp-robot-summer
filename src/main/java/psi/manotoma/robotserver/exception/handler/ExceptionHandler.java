package psi.manotoma.robotserver.exception.handler;

import psi.manotoma.robotserver.server.model.Response;
import java.io.OutputStream;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public interface ExceptionHandler<RSP extends Response, EX extends RuntimeException> {

    RSP handle(EX ex, OutputStream os);
    
}
