package psi.manotoma.robotserver.server.processor.provider;

import psi.manotoma.robotserver.server.processor.PreProcessor;
import psi.manotoma.robotserver.server.model.Request;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public interface PreProcessorsProvider<RQ extends Request> extends PreProcessor<RQ> {

    void add(PreProcessor<RQ> processor);
    
}
