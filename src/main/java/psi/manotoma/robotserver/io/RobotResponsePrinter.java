package psi.manotoma.robotserver.io;

import psi.manotoma.robotserver.io.RobotResponseOutputStream;
import psi.manotoma.robotserver.robot.RobotResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotResponsePrinter {

    public static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RobotResponsePrinter.class);

    public static String getAsString(RobotResponse res) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        RobotResponseOutputStream stream = new RobotResponseOutputStream(bytes);
        try {
            stream.write(res);
            stream.close();
        } catch (IOException ex) {
            LOG.error("an error occured while printing: {}", ex);
        }
        return bytes.toString();
    }
    
    public static void printToConsole(RobotResponse res) {
        System.out.println("=========================");
        System.out.println("Printing response:");
        System.out.println("=========================");
        System.out.println(getAsString(res));
        System.out.println("=========================");
    }
}
