package psi.manotoma.robotserver.satellite;

import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class Validator {

    private static final Logger LOG = LoggerFactory.getLogger(Validator.class);
    
    public static boolean validatePassword(String name, String password) {
        int sum = 0;
        byte[] asciiBytes = name.getBytes(Charsets.US_ASCII);
        for (int i = 0; i < asciiBytes.length; i++) {
            sum += asciiBytes[i];
        }
        LOG.debug("Validating pass {} against sum of asciiBytes {}", password, sum);
        if (sum != Integer.parseInt(password)) {
            return false;
        }
        return true;
    }
}
