package psi.manotoma.robotserver.server.support;

import java.io.InputStream;
import java.io.OutputStream;
import psi.manotoma.robotserver.satellite.SatelliteController;
import psi.manotoma.robotserver.io.RequestReader;
import psi.manotoma.robotserver.io.RobotRequestReader;
import psi.manotoma.robotserver.robot.RobotMsgsFactory;
import psi.manotoma.robotserver.robot.RobotResponse;
import psi.manotoma.robotserver.server.model.SetupRequest;
import psi.manotoma.robotserver.server.support.sender.RobotResponseSender;

public class RobotSetupConnectionTemplate extends ProtocolTemplate<SetupRequest, RobotResponse> {
    
    private boolean setupNameFinished;
    private boolean setupPasswordFinished;
    private int chances = 1;

    public RobotSetupConnectionTemplate(InputStream input, OutputStream output) {
        super(new RobotRequestReader(input), output);
    }

    @Override
    public SetupRequest parse(RequestReader source) {
        SetupRequest req = RobotMsgsFactory.createSetupRequest(source, robot);
        return req;
    }

    @Override
    public void preProcess(SetupRequest req) {
        //nothing
    }

    @Override
    public RobotResponse serve(SetupRequest req) {
        if (!setupNameFinished) {
            SatelliteController.setupName(robot, req.getLine());
            setupNameFinished = true;
            return RobotMsgsFactory.createPasswordResponse(robot);
        }
        if (!setupPasswordFinished) {
            chances--;
            SatelliteController.setupPassword(robot, req.getLine());
            setupPasswordFinished = true;
            return RobotMsgsFactory.createOKResponse(robot);
        }
        return RobotMsgsFactory.createOKResponse(robot);
    }

    @Override
    public void postProcess(SetupRequest req, RobotResponse res) {
        RobotResponseSender.getInstance().send(res, getOutputStream());
    }
    
    public boolean isNotFinnished(){
        return setupNameFinished && setupPasswordFinished;
    }
    
    public boolean hasChance() {
        return chances != 0;
    }
}
