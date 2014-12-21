package nl.arthurvlug.geneticAlgorithm.abTest;

import java.util.ArrayList;
import java.util.List;

import org.jgap.BaseGene;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;

@SuppressWarnings("serial")
public class AbTestGene extends BaseGene {
	private List<Integer> values;
	private final int size;

	public AbTestGene(final Configuration conf, final int size) throws InvalidConfigurationException {
		super(conf);
		this.size = size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setAllele(final Object newValue) {
		this.values = (List<Integer>) newValue;
	}

	@Override
	public String getPersistentRepresentation() throws UnsupportedOperationException {
		return values.toString();
	}

	@Override
	public void setValueFromPersistentRepresentation(final String a_representation) throws UnsupportedOperationException, UnsupportedRepresentationException {
		System.out.println("sadasd asdas");
	}

	@Override
	public void setToRandomValue(final RandomGenerator numberGenerator) {
		values = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			values.add(numberGenerator.nextInt(100));
		}
	}

	@Override
	public void applyMutation(final int index, final double a_percentage) {
		final int newValue = (int) Math.ceil(values.get(index) * (1+a_percentage));
		values.set(index, newValue);
	}

	@Override
	public int compareTo(final Object o) {
		final AbTestGene other = (AbTestGene) o;
		if(values == null && other.values == null) {
			return 0;
		}

		if(other.values == null || other.values.size() == 0) {
			return 1;
		} else if(values.size() == 0) {
			return -1;
		} else {
			Preconditions.checkArgument(values.size() == other.values.size());
			for (int i = 0; i < values.size(); i++) {
				final int diff = values.get(i) - other.values.get(i);
				if(diff != 0) {
					return diff;
				}
			}
			return 0;
		}
	}
	
	@Override
	public boolean equals(final Object a_other) {
		return compareTo(a_other) == 0;
	}

	@Override
	protected Object getInternalValue() {
		return values;
	}

	@Override
	protected Gene newGeneInternal() {
		try {
			return new AbTestGene(getConfiguration(), size);
		} catch (final InvalidConfigurationException e) {
			Throwables.propagate(e);
			return null;
		}
	}
	
	@Override
	public String toString() {
		return "AbTestGene: " + values.toString();
	}
	
	@Override
	public int size() {
		return values.size();
	}
}
