package nl.arthurvlug.geneticAlgorithm.helloWorld;

import nl.arthurvlug.geneticAlgorithm.Utils;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.StringGene;

public class HelloWorldGA {
	private static final String MESSAGE = "Hello, world!";
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ,!";

	private void start() throws InvalidConfigurationException {
		System.out.println("Start");
		final Genotype genotype = createGenePool(3);

		final long startTime = System.currentTimeMillis();
		IChromosome bestSolution = null;
		int iterations = 0;
		do {
			genotype.evolve();
			Utils.printCurrentPopulation(genotype.getPopulation());
			bestSolution = genotype.getFittestChromosome();
			iterations++;
		} while (bestSolution.getFitnessValue() != MESSAGE.length());
		final long endTime = System.currentTimeMillis();

		Utils.outputSolution(genotype, startTime, endTime, iterations);
	}

	private Genotype createGenePool(final int popSize) throws InvalidConfigurationException {
		final Configuration conf = new DefaultConfiguration();
		conf.setPreservFittestIndividual(true);

		final FitnessFunction myFunc = new HelloWorldFitnessFunction(MESSAGE);
		conf.setFitnessFunction(myFunc);

		final Gene stringGene = new StringGene(conf, 1, 1, ALPHABET);
		final IChromosome sampleChromosome = new Chromosome(conf, stringGene, MESSAGE.length());
		conf.setSampleChromosome(sampleChromosome);
		conf.setPopulationSize(popSize);

		Genotype population;
		population = Genotype.randomInitialGenotype(conf);

		return population;
	}

	public static void main(final String[] args) throws InvalidConfigurationException {
		final HelloWorldGA main = new HelloWorldGA();
		main.start();
	}
}
