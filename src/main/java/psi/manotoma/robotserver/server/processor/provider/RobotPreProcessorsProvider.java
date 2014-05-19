package psi.manotoma.robotserver.server.processor.provider;

import psi.manotoma.robotserver.robot.RobotRequest;
import psi.manotoma.robotserver.server.processor.PreProcessor;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotPreProcessorsProvider implements PreProcessorsProvider<RobotRequest> {

    private static Set<PreProcessor<RobotRequest>> preProcessors = new LinkedHashSet<PreProcessor<RobotRequest>>();

    private static PreProcessorsProvider<RobotRequest> INSTANCE = new RobotPreProcessorsProvider();
    
    private RobotPreProcessorsProvider() {
        
    }
    
    public static PreProcessorsProvider<RobotRequest> getInstance(){
        return INSTANCE;
    }
    

    public RobotRequest preProcess(RobotRequest req) {
        for (PreProcessor<RobotRequest> proc : preProcessors) {
            proc.preProcess(req);
        }
        return req;
    }
    
    public void add(PreProcessor<RobotRequest> processor){
        preProcessors.add(processor);
    }
}
