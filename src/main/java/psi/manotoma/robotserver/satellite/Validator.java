package psi.manotoma.robotserver.satellite;

import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psi.manotoma.robotserver.server.model.Photo;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class Validator {

    private static final Logger LOG = LoggerFactory.getLogger(Validator.class);

    public static boolean validatePassword(String name, String password) {
        int sum = getBytesSum(name);
        LOG.debug("Validating pass {} against sum of asciiBytes {}", password, sum);
        if (sum != Integer.parseInt(password)) {
            return false;
        }
        return true;
    }

    public static boolean validateChecksum(Photo photo) {
        String hex = formatChecksum(computeChecksum(photo));
        return hex.equals(photo.getChecksum()) ? true : false;

    }

    private static int getBytesSum(String data) {
        int sum = 0;
        byte[] asciiBytes = data.getBytes(Charsets.US_ASCII);
        for (int i = 0; i < asciiBytes.length; i++) {
            sum += asciiBytes[i];
        }
        return sum;
    }

    private static String formatChecksum(String s) {
        int requiredSize = 8 - s.length();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < requiredSize; i++) {
            sb.insert(i, "0");
        }
        for (int i = 0; i < 14; i = i + 4) {
            sb.insert(i, "\\x");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        boolean validateChecksum = validateChecksum(new Photo(""));
        System.out.println("RES: "+validateChecksum);
    }

    private static String computeChecksum(Photo photo) {
        int sum = getBytesSum(photo.getAsciiBytesString());
        return Integer.toHexString(sum);
    }
}
