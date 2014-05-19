package psi.manotoma.robotserver.io;

import psi.manotoma.robotserver.robot.RobotResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotResponseOutputStream extends OutputStream {

    private OutputStream os;

    public RobotResponseOutputStream(OutputStream os) {
        this.os = os;
    }

    @Override
    public void write(int b) throws IOException {
        os.write(b);
    }

    public void write(RobotResponse robotResponse) throws IOException {
        os.write(robotResponse.getResponseLine().getBytes());
        os.flush();
//        os.close();
    }
}