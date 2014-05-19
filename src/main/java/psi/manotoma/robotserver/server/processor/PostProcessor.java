package psi.manotoma.robotserver.server.processor;

import psi.manotoma.robotserver.server.model.Request;
import psi.manotoma.robotserver.server.model.Response;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public interface PostProcessor<RQ extends Request, RSP extends Response> {

    RSP postProcess(RQ req, RSP res);
}
