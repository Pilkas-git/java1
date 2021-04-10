package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class NumberGenerator implements Serializable {

    public Integer generateNumber() {
        Integer generatedNumber = new Random().nextInt(100);
        return generatedNumber;
    }
}