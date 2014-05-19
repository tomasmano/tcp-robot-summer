package psi.manotoma.robotserver.server.processor.provider;

import psi.manotoma.robotserver.robot.RobotRequest;
import psi.manotoma.robotserver.robot.RobotResponse;
import psi.manotoma.robotserver.server.processor.PostProcessor;
import java.util.LinkedHashSet;
import java.util.Set;


public class RobotPostProcessorsProvider implements PostProcessorsProvider<RobotRequest, RobotResponse> {
    
    private static Set<PostProcessor<RobotRequest, RobotResponse>> preProcessors = new LinkedHashSet<PostProcessor<RobotRequest, RobotResponse>>();
    
    private static PostProcessorsProvider<RobotRequest, RobotResponse> INSTANCE = new RobotPostProcessorsProvider();
    
    public static PostProcessorsProvider<RobotRequest, RobotResponse> getInstance(){
        return INSTANCE;
    }

    public RobotResponse postProcess(RobotRequest req, RobotResponse res) {
        for (PostProcessor<RobotRequest, RobotResponse> processor : preProcessors) {
            processor.postProcess(req, res);
        }
        return res;
    }

    public void add(PostProcessor<RobotRequest, RobotResponse> processor) {
        preProcessors.add(processor);
    }
    
}
