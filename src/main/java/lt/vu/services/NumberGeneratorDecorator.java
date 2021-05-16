package lt.vu.services;

import lt.vu.services.interfaces.INumberGenerator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class NumberGeneratorDecorator implements INumberGenerator {

    @Inject
    @Delegate
    @Any
    INumberGenerator numberGenerator;

    public Integer generateNumber() {
        try {
            Integer generatedNumber = numberGenerator.generateNumber();
            Thread.sleep(5000);
            System.out.println("DECORATOR TASK DONE");
            return generatedNumber;
        } catch (InterruptedException e) {
            return 0;
        }
    }
}