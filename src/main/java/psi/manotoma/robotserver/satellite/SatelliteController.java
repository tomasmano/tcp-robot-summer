package psi.manotoma.robotserver.satellite;

import psi.manotoma.robotserver.exception.BadSyntaxException;
import psi.manotoma.robotserver.exception.InvalidUsernameOrPasswordException;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class SatelliteController {
    
    public static void setupName(Robot robot, String name){
        try {
            robot.setName(name);
        } catch (IllegalArgumentException e) {
            throw new BadSyntaxException(String.format("Name connot be empty or null [%s].", name));
        }
    }
    
    public static void setupPassword(Robot robot, String password){
        try {
            robot.setPassword(password);
        } catch (IllegalArgumentException e) {
            throw new BadSyntaxException(String.format("Password connot be empty or null [%s].", password));
        } catch (InvalidUsernameOrPasswordException ex) {
            throw new InvalidUsernameOrPasswordException(String.format("Password [%s] for [%s] is invalid: ", new Object[]{password, robot, ex}));
        }
    }
    
    public static void acceptInfoMsg(Robot robot){
        //TODO
    }
    
    public static void acceptPhoto(Robot robot){
        //TODO
    }
    
}
