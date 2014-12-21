package nl.arthurvlug.geneticAlgorithm.helloWorld;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

@SuppressWarnings("serial")
public class HelloWorldFitnessFunction extends FitnessFunction {

    private char[] _expected;

    public HelloWorldFitnessFunction(String desiredMessage) {
        _expected = desiredMessage.toCharArray();
    }

    @Override
    protected double evaluate(IChromosome aSubject) {
        int fitnessValue = 0;

        for (int i = 0; i < aSubject.size(); i++) {
            String actual = (String) aSubject.getGene(i).getAllele();
            if (actual.toCharArray()[0] == _expected[i]) {
                fitnessValue += 1;
            }
        }

        return fitnessValue;
    }

}