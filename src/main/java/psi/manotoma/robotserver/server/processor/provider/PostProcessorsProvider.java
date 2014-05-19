package psi.manotoma.robotserver.server.processor.provider;

import psi.manotoma.robotserver.server.processor.PostProcessor;
import psi.manotoma.robotserver.server.model.Request;
import psi.manotoma.robotserver.server.model.Response;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public interface PostProcessorsProvider<RQ extends Request, RSP extends Response> extends PostProcessor<RQ, RSP> {

    void add(PostProcessor<RQ , RSP> processor);
    
}
