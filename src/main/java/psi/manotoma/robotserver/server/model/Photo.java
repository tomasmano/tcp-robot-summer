package psi.manotoma.robotserver.server.model;

import com.google.common.base.Charsets;


/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public class Photo {
    private String asciiBytes;
    private String checksum;
    private int size;

    public Photo(String asciiBytes) {
        this.asciiBytes = asciiBytes;
    }

    public Photo(String asciiBytes, int size) {
        this.asciiBytes = asciiBytes;
        this.size = size;
    }

    public byte[] getAsciiBytes() {
        return asciiBytes.getBytes(Charsets.US_ASCII);
    }

    public String getAsciiBytesString() {
        return asciiBytes;
    }

    public String getChecksum() {
        return checksum;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Photo{" + "checksum=" + checksum + ", size=" + size + '}';
    }
    
}
