package psi.manotoma.robotserver.satellite;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import psi.manotoma.robotserver.exception.InvalidUsernameOrPasswordException;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class Robot {
    
    private static final String EMPTY_STRING = "";
    
    private String name;
    private String password;
    private List<String> infoMsgs;
    private List<String> photoFiles;
    
    private Robot() {
        this.name = EMPTY_STRING;
        this.password = EMPTY_STRING;
        this.infoMsgs = new ArrayList<String>();
        this.photoFiles = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Password cannot be empty or null");
        }
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("Password cannot be empty or null");
        }
        if (!Validator.isValidPassword(name, password)) {
            throw new InvalidUsernameOrPasswordException(String.format("Password [%s] for [%s] is invalid", password, this));
        }
        this.password = password;
    }

    public List<String> getInfoMsgs() {
        return infoMsgs;
    }

    public List<String> getPhotoFiles() {
        return photoFiles;
    }

    public static Robot generate(){
        return new Robot();
    }

    @Override
    public String toString() {
        return "Robot{" + "name=" + name + ", password=" + password + ", infoMsgs=" + infoMsgs + ", photoFiles=" + photoFiles + '}';
    }

}
