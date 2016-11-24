package gb;

import java.util.ArrayList;

public class Chromosome {
	private StringBuffer stringBuffer = new StringBuffer();
	private ArrayList<Gene>geneList;
	private String chromosomeAsString;
	private Double fitnessScore;
	private Integer genesNumber;
	private Integer lifeLeft;	
	
	public Chromosome() {
		super();
		this.geneList = new ArrayList<Gene>();
		this.chromosomeAsString = null;
		this.fitnessScore = 0.000000;
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
		if(chromosomeAsString != null){
			for (Gene gene : this.getGeneList()) {
				stringBuffer.append(gene.getCode());
			}	
		}
		chromosomeAsString = stringBuffer.toString();
		stringBuffer = null;
		return chromosomeAsString;
	}
	
	public void calculateFitness(){
		if(fitnessScore>0.0)fitnessScore=0.0;
		int a = 0;
		String lastGene="";
		for (Gene gene : geneList) {
			if(a==0)lastGene=gene.decode();
			if((gene.decode().equals("*"))&&((a==0)||(a==genesNumber-1))){
				lastGene = gene.decode();
				a++;
				continue;
			}
			if(gene.decode().equals("N/A")){
				lastGene = gene.decode();
				a++;
				continue;
			}
			if(!(gene.decode().equals("*"))&&(a>1)&&(lastGene.equals("*"))){
				fitnessScore *= Integer.valueOf(gene.decode());
				lastGene = gene.decode();
				a++;
				continue;
			}
			if(gene.decode().equals("*")&&lastGene.equals("*")){
				lastGene = gene.decode();
				a++;
				continue;
			}
			else{
				fitnessScore += Integer.valueOf(gene.decode());
				lastGene = gene.decode();
			}
			a++;
		}
		lastGene="";
		a=0;
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
		return chromosomeAsString;
	}

	public void setChromosome(String chromosome) {
		this.chromosomeAsString = chromosome;
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

	public Integer getLifeLeft() {
		return lifeLeft;
	}

	public void setLifeLeft(Integer lifeLeft) {
		this.lifeLeft = lifeLeft;
	}
	
	
	
}
