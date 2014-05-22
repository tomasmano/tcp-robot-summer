package psi.manotoma.robotserver.satellite;

import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import psi.manotoma.robotserver.exception.BadSyntaxException;
import psi.manotoma.robotserver.exception.InvalidChecksumException;
import psi.manotoma.robotserver.exception.InvalidUsernameOrPasswordException;
import psi.manotoma.robotserver.robot.RobotRequest;
import static psi.manotoma.robotserver.satellite.Validator.isValidChecksum;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class SatelliteController {
    
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SatelliteController.class);

    public static void setupName(Robot robot, String name) {
        try {
            robot.setName(name);
        } catch (IllegalArgumentException e) {
            throw new BadSyntaxException(String.format("Name connot be empty or null [%s].", name));
        }
    }

    public static void setupPassword(Robot robot, String password) {
        try {
            robot.setPassword(password);
        } catch (IllegalArgumentException e) {
            throw new BadSyntaxException(String.format("Password connot be empty or null [%s].", password));
        } catch (InvalidUsernameOrPasswordException ex) {
            throw new InvalidUsernameOrPasswordException(String.format("Password [%s] for [%s] is invalid: ", new Object[]{password, robot, ex}));
        }
    }

    public static void acceptInfoMsg(RobotRequest req, Robot robot) {
        LOG.debug("Accepting info msg [{}] for robot [{}]", req.getMsg(), robot.getName());
        robot.getInfoMsgs().add(req.getMsg());
        LOG.debug("Msg accepted for robot [{}]", robot);
    }

    public static void acceptPhoto(RobotRequest req, Robot robot) {
        LOG.debug("Accepting info photo [size: {}] for robot [{}]", req.getPhoto().getSize(), robot.getName());
        if (!isValidChecksum(req.getPhoto())) {
            throw new InvalidChecksumException(String.format("Checksum [%s] is invalid", req.getPhoto().getChecksum()));
        }
        String filename = generatePhotoFileName();
        saveToFile(filename, req.getPhoto().getAsciiBytes());
    }

    private static String generatePhotoFileName() {
        StringBuilder sb = new StringBuilder("foto");
        return sb.append(RandomUtils.nextInt(0, 999)).append(".png").toString();
    }

    private static void saveToFile(String name, byte[] data) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name);
            out.write(data);
            out.close();
        } catch (IOException ex) {
            LOG.error("An error occured when saving file: {}", ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                LOG.error("An error occured when cloasing stream: {}", ex);
            }
        }
    }
}
