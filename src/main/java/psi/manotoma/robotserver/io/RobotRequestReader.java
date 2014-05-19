package psi.manotoma.robotserver.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class RobotRequestReader implements RequestReader {
    
    private BufferedReader br;

    public RobotRequestReader(InputStream is) {
        this.br = new BufferedReader(new InputStreamReader(is));
    }
    
    @Override
    public String read() throws IOException {
        return br.readLine();
    }
    
}
