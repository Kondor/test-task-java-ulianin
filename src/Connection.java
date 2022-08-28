import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

public class Connection {
    public final String ADDRESS_SITE = "http://numbersapi.com/";
    public String stringAddress; //
    public long numberRandom;
    public URL url;

    public Connection() throws MalformedURLException {
        this.numberRandom = new Random().nextInt();
        this.stringAddress = ADDRESS_SITE + numberRandom + "/trivia";
        this.url = new URL(stringAddress);
    }

    public long getNumberRandom() {
        return numberRandom;
    }

    public URL getUrl() {
        return url;
    }
}
