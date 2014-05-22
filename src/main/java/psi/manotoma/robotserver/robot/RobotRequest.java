package psi.manotoma.robotserver.robot;

import psi.manotoma.robotserver.satellite.Robot;
import psi.manotoma.robotserver.server.model.Photo;
import psi.manotoma.robotserver.server.model.Request;
import static psi.manotoma.robotserver.server.support.RobotRequestParser.parseForCommand;
import static psi.manotoma.robotserver.server.support.RobotRequestParser.parseLine;
import static psi.manotoma.robotserver.server.support.RobotRequestParser.parsePhotoMsg;

public class RobotRequest implements Request {

    private Command cmd;
    private String msg;
    private Photo photo;
    private Robot robot;

    public RobotRequest(String input, Robot robot) {
        String[] line = parseLine(input);
//        if (!line[0].equals(robot.getName())) {
//            throw new BadSyntaxException(String.format("Invalid name [%s] for robot [%s]", line[0], robot));
//        }
        this.cmd = parseForCommand(line[0]);
        if (this.cmd.equals(Command.INFO)) {
            this.msg = line[1];
        }
        if (this.cmd.equals(Command.FOTO)) {
            this.msg = line[1];
            this.photo = parsePhotoMsg(msg);
        }
        this.robot = robot;
    }

    public Command getCommand() {
        return cmd;
    }

    public String getMsg() {
        return msg;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Robot getRobot() {
        return robot;
    }

    @Override
    public String toString() {
        return "RobotRequest{" + "cmd=" + cmd + ", robot=" + robot + '}';
    }

    //////////  Inner Class  //////////
    public enum Command {

        INFO, FOTO;
    }
}
