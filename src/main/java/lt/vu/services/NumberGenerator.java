package lt.vu.services;

import lt.vu.services.interfaces.INumberGenerator;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class NumberGenerator implements Serializable, INumberGenerator {

    public Integer generateNumber() {
        try {
            Thread.sleep(3000);
            System.out.println("THREAD SLEEP DONE");
        } catch (InterruptedException e) {

        }
        Integer generatedNumber = new Random().nextInt(100);
        return generatedNumber;
    }
}