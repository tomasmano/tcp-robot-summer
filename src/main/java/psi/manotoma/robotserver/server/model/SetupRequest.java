package psi.manotoma.robotserver.server.model;

import psi.manotoma.robotserver.satellite.Robot;
import static psi.manotoma.robotserver.server.support.RobotRequestParser.parseUsernameOrPasswordCommand;


public class SetupRequest implements Request {
    
    private Robot robot;
    private String line;

    public SetupRequest(String input, Robot robot) {
        this.line = parseUsernameOrPasswordCommand(input);
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "SetupRequest{" + "robot=" + robot + ", line=" + line + '}';
    }
    
    
}
