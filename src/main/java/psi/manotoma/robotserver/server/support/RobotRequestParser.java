package psi.manotoma.robotserver.server.support;

import java.util.Scanner;
import psi.manotoma.robotserver.exception.TerminatedConnectionException;
import psi.manotoma.robotserver.robot.RobotRequest;
import psi.manotoma.robotserver.server.model.Photo;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotRequestParser {

    private static final int CHECKSUM_LENGTH = 8;

    public static String[] parseLine(String line) {
        Scanner sc = null;
        try {
            sc = new Scanner(line);
        } catch (Exception e) {
            throw new TerminatedConnectionException("Client terminated connection.");
        }
        String cmd = sc.next();
        String msg = sc.nextLine().trim();
        sc.close();
        return new String[]{cmd, msg};
    }

    public static RobotRequest.Command parseForCommand(String cmdPart) {
        return RobotRequest.Command.valueOf(cmdPart.trim());
    }

    public static Photo parsePhotoMsg(String msg) {
        int space = msg.indexOf(" ") + 1;
        String sizeTxt = msg.substring(0, space).trim();
        int size = Integer.parseInt(sizeTxt);
        String data = msg.substring(space).trim();
        String photoData = data.substring(0, data.length() - CHECKSUM_LENGTH);
        String checksum = data.substring(data.length() - CHECKSUM_LENGTH);
        return new Photo(photoData, checksum, size);
    }

    public static String parseUsernameOrPasswordCommand(String cmd) {
        return cmd;
    }
//    public static void main(String[] args) {
//        System.out.println(Validator.isValidChecksum(new RobotRequest("FOTO 8 ABCDEFGH00000224", null).getPhoto()));
//    }
}
