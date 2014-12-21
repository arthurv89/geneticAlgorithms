package nl.arthurvlug.geneticAlgorithm.abTest;

import java.util.List;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

@SuppressWarnings("serial")
public class AbTestFitnessFunction extends FitnessFunction {
	private final Function<List<Integer>, Double> scoreFunction = new Function<List<Integer>, Double>() {
		@Override
		public Double apply(final List<Integer> val) {
			Preconditions.checkArgument(val.size() == best.size());
			
			double totalDiff = 1.0;
			for (int i = 0; i < val.size(); i++) {
				final int diff = Math.abs(val.get(i) - best.get(i));
				totalDiff += (diff +1);
			}
			return 1 / totalDiff;
		}
	};
	
	private final ImmutableList<Integer> best;

    public AbTestFitnessFunction(final ImmutableList<Integer> best) {
		this.best = best;
	}

	@Override
    protected double evaluate(final IChromosome aSubject) {
    	double sum = 0.0;
    	for (int i = 0; i < aSubject.size(); i++) {
    		@SuppressWarnings("unchecked")
			final
			List<Integer> allele = (List<Integer>) aSubject.getGene(i).getAllele();
			sum += scoreFunction.apply(allele);
		}
    	return sum;
    }
}