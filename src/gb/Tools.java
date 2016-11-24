package gb;

import java.util.ArrayList;

import java.util.Random;

/**
 * @author aphanel
 *
 * Utility class for GA
 */
public class Tools {
	
	/**
	 * @param chromosomePool
	 * @return index i of ArrayList chromosomePool
	 * 
	 * Variation of roulette selection algorithm found on https://en.wikipedia.org/wiki/Fitness_proportionate_selection
	 * 
	 * Basically, the highest the fitnessScore of a given chromosome, the more likely it is to be chosen.
	 */
	public Integer rouletteSelect(ArrayList<Chromosome> chromosomePool) {
		
		// Iterate over chromosomePool to populate weight ArrayList with fitnessScore		
		ArrayList<Double> weight = new ArrayList<Double>();
		for (Chromosome chromosome : chromosomePool) {
			weight.add(chromosome.getFitnessScore());			
		}
		// Returns the selected index based on the weights(probabilities)
		// calculate the total weight
		Double weight_sum = 0.000000;
		for(int i=0; i<weight.size(); i++) {
			weight_sum += weight.get(i);
		}
		System.out.println("WEIGHT : "+weight_sum);
		// get a random value
		Double value = randUniformPositive() * weight_sum;	
		System.out.println("RAND : "+value);
		// locate the random value based on the weights
		for(int i=0; i<weight.size(); i++) {		
			value -= weight.get(i);		
			if(value <= 0) return i;
		}
		// when rounding errors occur, we return the last item's index 
		return weight.size();
	}

	// Returns a uniformly distributed double value between 0.0 and 1.0
	public Double randUniformPositive() {
		
		return new Random().nextDouble();
	}
}
