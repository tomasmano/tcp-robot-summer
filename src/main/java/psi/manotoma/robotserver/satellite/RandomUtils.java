package psi.manotoma.robotserver.satellite;

import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class RandomUtils {
    
    private static RandomData gen = new RandomDataImpl();
    
    public static int nextInt(int lower, int upper){
        return gen.nextInt(lower, upper);
    }
}
