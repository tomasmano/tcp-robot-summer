package psi.manotoma.robotserver.server.support;

import psi.manotoma.robotserver.satellite.Robot;
import psi.manotoma.robotserver.robot.RobotMsgsFactory;
import psi.manotoma.robotserver.robot.RobotResponse;
import psi.manotoma.robotserver.robot.StatusUtils;
import psi.manotoma.robotserver.server.support.sender.RobotResponseSender;
import java.io.IOException;
import java.net.Socket;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server task. Command object.
 * 
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RobotServerTask implements Runnable {
    
    public static final Logger LOG = LoggerFactory.getLogger(RobotServerTask.class);
    
    private RobotProtocolTemplate template;
    private RobotSetupConnectionTemplate setupTemplate;
    private Socket client;
    
    private Robot robot;
    
    public RobotServerTask(RobotProtocolTemplate template, RobotSetupConnectionTemplate setupTemplate, Socket client) {
        robot = Robot.generate();
        this.template = template;
        this.setupTemplate = setupTemplate;
        this.client = client;
        this.template.setRobot(robot);
        this.setupTemplate.setRobot(robot);
        
    }
    
    public void run() {
        process();
    }

    public void process() {
        long startSession = System.currentTimeMillis();
        
        RobotResponse res = null;
        
        login();
        while(!setupTemplate.isNotFinnished() && setupTemplate.hasChance()){
            setupTemplate.doTemplate();
        }
        
        if (!setupTemplate.isNotFinnished()) {
            close(startSession, res);
            return;
        }
        
        res = template.doTemplate();
        
        while (!StatusUtils.isCloseConnection(res.getStatus())) {
            long start = System.currentTimeMillis();
            res = template.doTemplate();
            long end = System.currentTimeMillis();
            long diff = end - start;
            LOG.info("Finished [{} ms]: {}", diff, res);
        }
//        try {
//            IOUtils.closeQuietly(client.getOutputStream());
//        } catch (IOException ex) {
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//        }
//        try {
//            IOUtils.closeQuietly(client.getInputStream());
//        } catch (IOException ex) {
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//            System.err.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
//        }
        
        close(startSession, res);

    }

    private void login() {
        try {
            RobotResponseSender.getInstance().send(RobotMsgsFactory.createLoginResponse(robot), client.getOutputStream());
        } catch (IOException ex) {
            LOG.error("An exception occured: {}", ex);
            IOUtils.closeQuietly(client);
        }
        
    }
    
    private void close(long startSession, RobotResponse res){
        IOUtils.closeQuietly(client);
        
        long endSession = System.currentTimeMillis();
        long diffSession = endSession - startSession;
        LOG.info("Session finished [{} ms]: {}", diffSession, res);
    }
    
}
