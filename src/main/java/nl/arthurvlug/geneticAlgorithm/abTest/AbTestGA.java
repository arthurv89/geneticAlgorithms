package nl.arthurvlug.geneticAlgorithm.abTest;

import nl.arthurvlug.geneticAlgorithm.Utils;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;

import com.google.common.collect.ImmutableList;

public class AbTestGA {
	private static final int POPULATION_SIZE = 100;
	private static final ImmutableList<Integer> BEST = ImmutableList.<Integer> of(1, 2, 4, 8);
	
	private void start() throws InvalidConfigurationException {
		final Genotype genotype = createGenePool();

		do {
			genotype.evolve();
			Utils.printCurrentPopulation(genotype.getPopulation());
		} while (true);
	}

	private Genotype createGenePool() throws InvalidConfigurationException {
		final Configuration conf = new DefaultConfiguration();
		conf.setPreservFittestIndividual(true);
		conf.setFitnessFunction(new AbTestFitnessFunction(BEST));
		conf.setPopulationSize(POPULATION_SIZE);

		final Gene gene = new AbTestGene(conf, BEST.size());
		conf.setSampleChromosome(new Chromosome(conf, gene, BEST.size()));

		return Genotype.randomInitialGenotype(conf);
	}

	public static void main(final String[] args) throws InvalidConfigurationException {
		new AbTestGA().start();
	}
}
