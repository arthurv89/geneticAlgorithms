package nl.arthurvlug.geneticAlgorithm;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.Population;

public class Utils {
	private static final NumberFormat nf = DecimalFormat.getNumberInstance(); {
		nf.setMaximumFractionDigits(4);
	}
	
	public static void outputSolution(final Genotype population, final long startTime, final long endTime, final int evolutionIdx) {
		System.out.println("Stopped at generation " + evolutionIdx);

		final long totalSeconds = (endTime - startTime) / 1000;
		final long actualSeconds = totalSeconds % 60;
		final long actualMinutes = totalSeconds / 60;
		System.out.println("Total evolution time: " + actualMinutes + ":" + actualSeconds);

		final IChromosome solution = population.getFittestChromosome();
		final int fitnessValue = (int) solution.getFitnessValue();
		System.out.println("The best solution has a fitness value of " + fitnessValue);

		final String message = getMessage(solution);
		System.out.println("\t '" + message + "'");
	}

	private static String getMessage(final IChromosome solution) {
		String result = "";

		final int length = solution.size();
		for (int i = 0; i < length; i++) {
			result += (String) solution.getGene(i).getAllele();
		}

		return result;
	}

	public static void printCurrentPopulation(final Population population) {
		for (int i = 0; i < population.size(); i++) {
			final IChromosome chromosome = population.getChromosome(i);
			final double fitnessValue = chromosome.getFitnessValue();
			System.out.print(nf.format(fitnessValue));
			System.out.print('=');
			for (final Gene gene : chromosome.getGenes()) {
				System.out.print(gene.getAllele());
			}
			System.out.print('\t');
		}
		System.out.println();
	}
}
