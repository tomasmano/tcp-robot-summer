package psi.manotoma.robotserver.robot;

import psi.manotoma.robotserver.satellite.Robot;
import psi.manotoma.robotserver.exception.BadSyntaxException;
import psi.manotoma.robotserver.io.RequestReader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import psi.manotoma.robotserver.exception.TerminatedConnectionException;
import psi.manotoma.robotserver.server.model.SetupRequest;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotMsgsFactory {

    public static final Logger LOG = LoggerFactory.getLogger(RobotMsgsFactory.class);

    private RobotMsgsFactory() {
        // to prevent instantiation
    }

    public static RobotRequest createRequest(RequestReader reader, Robot robot) {
        RobotRequest request = null;
        try {
            LOG.debug("Parsing request..");
            String firstLine = reader.read();
            request = new RobotRequest(firstLine, robot);  
            LOG.debug("Parsing finished.");
        } catch (TerminatedConnectionException ex) {
            LOG.error("Error occured during parsing: {}", ex);
            throw ex;
        } catch (Exception ex) {
            LOG.error("Error occured during parsing: {}", ex);
            throw new BadSyntaxException("Bad syntax");
        } 
        return request;
    }

    public static SetupRequest createSetupRequest(RequestReader reader, Robot robot) {
        SetupRequest request = null;
        try {
            LOG.debug("Parsing request..");
            String firstLine = reader.read();
            request = new SetupRequest(firstLine, robot);  
            LOG.debug("Parsing finished.");
        } catch (TerminatedConnectionException ex) {
            LOG.error("Error occured during parsing: {}", ex);
            throw ex;
        } catch (Exception ex) {
            LOG.error("Error occured during parsing: {}", ex);
            throw new BadSyntaxException("Bad syntax");
        } 
        return request;
    }
    
    public static RobotResponse createLoginResponse(Robot robot){
        return new RobotResponse(Status._200);
    }

    public static RobotResponse createPasswordResponse(Robot robot){
        return new RobotResponse(Status._201);
    }

    public static RobotResponse createOKResponse(Robot robot){
        return new RobotResponse(Status._202);
    }

//    public static RobotResponse createResponse(Status status, Robot robot) {
//        return new RobotResponse(status, robot.getName(), robot.getCoordinates());
//    }

    public static RobotResponse createErrorResponse(Status code) {
        return new RobotResponse(code);
    }


}
