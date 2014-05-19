package psi.manotoma.robotserver.robot;

import psi.manotoma.robotserver.server.model.Response;
import static psi.manotoma.robotserver.robot.StatusUtils.isConnectionTerminated;
import static psi.manotoma.robotserver.robot.StatusUtils.hasError;


public class RobotResponse implements Response {

    private Status status;
    private String responseLine;
    private String robotName;
    private int nProc;
    
    private final static String CRLF = "\r\n"; // TERMINATOR

    public RobotResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getResponseLine() {
        if (hasError(status)) {
            responseLine = status.qName() + CRLF;
            return responseLine;
        }
        if (isConnectionTerminated(status)) {
            responseLine = status.qName() + CRLF;
            return responseLine;
        }
        responseLine = status.qName() + CRLF;
        return responseLine;
    }

    @Override
    public String toString() {
        return "RobotResponse{" + "responseLine=" + responseLine + ", robotName=" + robotName + '}';
    }
    
}
