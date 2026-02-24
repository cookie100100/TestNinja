package utilities;

import org.openqa.selenium.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScenarioData {
    private static final Logger log = LoggerFactory.getLogger(ScenarioData.class);
    private ScenarioData(){}
    private static final ThreadLocal<Credentials> CREDS=new ThreadLocal<>();
    public static void setCredentials(String email, String password){
        log.debug("Storing credentials (email={})", email);
        CREDS.set(new Credentials(email, password));
    }
    public static Credentials getCredentials() {
        Credentials credentials = CREDS.get();
        if(credentials == null){
            log.warn("Attempted to retrive credentials but none stored yet");
            throw new IllegalStateException("Credentials not found in ScenarioData. Did registration run?");
        }
        log.debug("Retrieved credentials");
        return credentials;
    }
    public static void clear(){
        log.debug("Clearing credentials");
        CREDS.remove();
    }
    public record Credentials(String email, String password){}
}
