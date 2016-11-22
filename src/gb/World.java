package gb;

import java.util.ArrayList;

public class World {
	public final static Double FITNESS_GOAL = 50.000000;
	public final static Double CROSSOVER_VALUE = 0.700000;
	public final static Double MUTATION_VALUE = 0.001000;
	public final static Integer	LIFE_EXPECTANCY = 5;
	public final static Double NUMBER_OF_STARTING_CHROMOSOMES = 1000.000000;
	private Double numberOfChromosomes;
	private Integer threshold;
	private Double probability;
	private ArrayList<Double> weight;
	private Tools toolbox;
	private ArrayList<Chromosome> chromosomePool;
	private Integer index1;
	private Integer index2;;
	
	
	public World() {
		super();
		this.numberOfChromosomes = NUMBER_OF_STARTING_CHROMOSOMES;
		this.threshold = 0;
		this.probability = 0.000000;
		this.weight = new ArrayList<Double>();	
		this.toolbox = new Tools();
		this.chromosomePool = new ArrayList<Chromosome>();	
		this.index1 = 0;
		this.index2 = 0;;
	}

	public void init(){
		for (int i = 0; i <NUMBER_OF_STARTING_CHROMOSOMES; i++) {			
			Chromosome chromosome = new Chromosome();
			chromosome.init();
			chromosomePool.add(chromosome);
			
			//Following is gibberish... rouletteSelect doesn't work as wanted
			probability = ((1.000000*100.000000)/numberOfChromosomes)/100;
			probability/=1.000000/chromosome.getFinalFitnessScore();
			probability*=2.000000;
			weight.add(probability);
		}
	}
	
	public void run(){
		while(threshold<NUMBER_OF_STARTING_CHROMOSOMES*50){
			mate();
		}
	}
	public void mate(){
		index1 = toolbox.rouletteSelect(weight);
		index2 = toolbox.rouletteSelect(weight);
		//Crossover
		if(toolbox.randUniformPositive()<=CROSSOVER_VALUE)crossover(index1, index2);
		//mutate
		if(toolbox.randUniformPositive()<=MUTATION_VALUE)mutate(index1);
		if(toolbox.randUniformPositive()<=MUTATION_VALUE)mutate(index2);
	}
	
	// exchange a random number of genes
	public void crossover(Integer i1, Integer i2){		
		Integer m = (int) (Math.random()*28);
		if(m<=3)swap(chromosomePool.get(i1),chromosomePool.get(i2));
		else if(m>3||m<=7)for (int i = 1; i < chromosomePool.get(i1).getGeneList().size(); i++) {
			swap(chromosomePool.get(i1).getGeneList().get(i),chromosomePool.get(i2).getGeneList().get(i),i);
		}
		else if(m>7||m<=11)for (int i = 2; i < chromosomePool.get(i1).getGeneList().size(); i++) {
			swap(chromosomePool.get(i1).getGeneList().get(i),chromosomePool.get(i2).getGeneList().get(i),i);
		}
		else if(m>11||m<=15)for (int i = 3; i < chromosomePool.get(i1).getGeneList().size(); i++) {
			swap(chromosomePool.get(i1).getGeneList().get(i),chromosomePool.get(i2).getGeneList().get(i),1);
		}
		else if(m>15||m<=19)for (int i = 4; i < chromosomePool.get(i1).getGeneList().size(); i++) {
			swap(chromosomePool.get(i1).getGeneList().get(i),chromosomePool.get(i2).getGeneList().get(i),1);
		}
		else if(m>19||m<=23)for (int i = 5; i < chromosomePool.get(i1).getGeneList().size(); i++) {
			swap(chromosomePool.get(i1).getGeneList().get(i),chromosomePool.get(i2).getGeneList().get(i),1);
		}
		else if(m>23||m<=27)for (int i = 6; i < chromosomePool.get(i1).getGeneList().size(); i++) {
			swap(chromosomePool.get(i1).getGeneList().get(i),chromosomePool.get(i2).getGeneList().get(i),1);
		}
		chromosomePool.get(i1).calculateFitness();
		chromosomePool.get(i2).calculateFitness();
		threshold++;
		
	}
	
	//Mutate a random gene
	public void mutate(Integer index){		
		Integer m = (int) (Math.random()*28);
		if(m<=3){
			chromosomePool.get(index).getGeneList().get(0).setRandomGene();
		}
		else if(m>3||m<=7)chromosomePool.get(index).getGeneList().get(1).setRandomGene();
		else if(m>7||m<=11)chromosomePool.get(index).getGeneList().get(2).setRandomGene();
		else if(m>11||m<=15)chromosomePool.get(index).getGeneList().get(3).setRandomGene();
		else if(m>15||m<=19)chromosomePool.get(index).getGeneList().get(4).setRandomGene();
		else if(m>19||m<=23)chromosomePool.get(index).getGeneList().get(5).setRandomGene();
		else if(m>23||m<=27)chromosomePool.get(index).getGeneList().get(6).setRandomGene();
		chromosomePool.get(index).calculateFitness();
		threshold++;
	}
	
	//useless for now. Maybe when life expectancy and children exist
	void swap(Chromosome c1, Chromosome c2){
		Chromosome temp = c1;
		c1 = c2;
		c2 = temp;
	}
	
	void swap(Gene c1, Gene c2, Integer i){
		Gene temp = c1;
		c1.setCode(c2.getCode());
		c2.setCode(temp.getCode());
	}
	
	public Tools getToolbox() {
		return toolbox;
	}

	public void setToolbox(Tools toolbox) {
		this.toolbox = toolbox;
	}

	public ArrayList<Chromosome> getChromosomePool() {
		return chromosomePool;
	}

	public void setChromosomePool(ArrayList<Chromosome> chromosomePool) {
		this.chromosomePool = chromosomePool;
	}

	public static Double getFitnessGoal() {
		return FITNESS_GOAL;
	}

	public static Double getCrossoverValue() {
		return CROSSOVER_VALUE;
	}

	public static Double getMutationValue() {
		return MUTATION_VALUE;
	}	
	
}
