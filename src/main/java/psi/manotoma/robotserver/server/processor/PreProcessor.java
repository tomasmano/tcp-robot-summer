package psi.manotoma.robotserver.server.processor;

import psi.manotoma.robotserver.server.model.Request;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public interface PreProcessor<RQ extends Request> {

    RQ preProcess(RQ req);
}
