package gb;

import java.util.ArrayList;

public class Chromosome {
	private ArrayList<Gene>geneList = new ArrayList<Gene>();
	private StringBuffer stringBuffer = new StringBuffer();
	private String chromosome = null;
	private Double fitnessScore = 0.000000;
	private Double finalFitnessScore = 0.000000;
	private Integer genesNumber = 7;
	private Integer lifeLeft = World.LIFE_EXPECTANCY;	
	
	public Chromosome() {
		super();
		this.geneList = new ArrayList<Gene>();
		this.chromosome = null;
		this.fitnessScore = 0.000000;
		this.finalFitnessScore = 0.000000;
		this.genesNumber = 7;
		this.lifeLeft = World.LIFE_EXPECTANCY;
	}



	public void init(){
		for (int i = 0; i <genesNumber; i++) {
			Gene gene = new Gene();
			geneList.add(gene);
			calculateFitness();
		}
	}
	
	public String displayGeneListAsString(){
		if(chromosome != null){
			for (Gene gene : this.getGeneList()) {
				stringBuffer.append(gene.getCode());
			}	
		}
		chromosome = stringBuffer.toString();
		stringBuffer = null;
		return chromosome;
	}
	
	public void calculateFitness(){
		for (Gene gene : geneList) {
			if(!(gene.decode().equals("N/A"))) stringBuffer.append(gene.decode());
		}
		fitnessScore = Tools.eval(stringBuffer.toString());
		if((fitnessScore == null)||(fitnessScore<=0.000000||(fitnessScore>=100.000000)||(!(Double.isFinite(fitnessScore)))))fitnessScore = 0.000000;
		finalFitnessScore = Math.abs(Math.ceil(World.FITNESS_GOAL - fitnessScore));
	}

	public ArrayList<Gene> getGeneList() {
		return geneList;
	}

	public void setGeneList(ArrayList<Gene> geneList) {
		this.geneList = geneList;
	}

	public StringBuffer getStringBuffer() {
		return stringBuffer;
	}

	public void setStringBuffer(StringBuffer stringBuffer) {
		this.stringBuffer = stringBuffer;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public Double getFitnessScore() {
		return fitnessScore;
	}

	public void setFitnessScore(Double fitnessScore) {
		this.fitnessScore = fitnessScore;
	}

	public Integer getGenesNumber() {
		return genesNumber;
	}

	public void setGenesNumber(Integer genesNumber) {
		this.genesNumber = genesNumber;
	}

	public Double getFinalFitnessScore() {
		return finalFitnessScore;
	}

	public void setFinalFitnessScore(Double finalFitnessScore) {
		this.finalFitnessScore = finalFitnessScore;
	}

	public Integer getLifeLeft() {
		return lifeLeft;
	}

	public void setLifeLeft(Integer lifeLeft) {
		this.lifeLeft = lifeLeft;
	}
	
	
	
}
