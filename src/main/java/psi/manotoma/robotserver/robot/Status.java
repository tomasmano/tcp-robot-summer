package psi.manotoma.robotserver.robot;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
public enum Status {
    
    _200("200 LOGIN", "200"),
    _201("201 PASSWORD", "201"),
    _202("202 OK", "202"),
    _300("300 BAD CHECKSUM", "300"),
    _500("500 LOGIN FAILED", "500"),
    _501("501 SYNTAX ERROR", "501"),
    _502("502 TIMEOUT", "502");
    
    private String qName;
    private String code;

    private Status(String qName, String code) {
        this.qName = qName;
        this.code = code;
    }

    public String qName() {
        return qName;
    }

    public String code() {
        return code;
    }
    
    public boolean isSameAs(Status status){
        return this.equals(status);
    }

    public boolean isSameAs(Status... sts){
        for (Status s : sts) {
            if (this.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
}
