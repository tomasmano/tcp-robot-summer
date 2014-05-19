package psi.manotoma.robotserver.server.support;

import java.util.Scanner;
import psi.manotoma.robotserver.exception.TerminatedConnectionException;
import psi.manotoma.robotserver.robot.RobotRequest;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotRequestParser {

    public static String[] parseLine(String line){
        Scanner sc = null;
        try {
            sc = new Scanner(line);
        } catch (Exception e) {
            throw new TerminatedConnectionException("Client terminated connection.");
        }
        String name = sc.next();
        String command = sc.nextLine().trim();
        sc.close();
        return new String[]{name, command};
    }

    public static RobotRequest.Command parseForCommand(String cmdPart){
        int end = cmdPart.indexOf(" ");
        String cmd = cmdPart.substring(0, end == -1 ? cmdPart.length() : end);
        return RobotRequest.Command.valueOf(cmd.trim());
    }

    public static String parseInfoCommand(String cmd){
        return parseLine(cmd)[1];
    }
    
    public static int parsePhotoCommand(String cmd){
        int medzeraaaa = cmd.indexOf(" ") + 1;
        String cisiiisllooo = cmd.substring(medzeraaaa).trim();
        return Integer.parseInt(cisiiisllooo);
    }
    
    public static String parseUsernameOrPasswordCommand(String cmd){
        return cmd;
    }

}
