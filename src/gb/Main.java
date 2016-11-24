package gb;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException, FileNotFoundException, IOException {
		//Instantiate and init the world
		World world = new World();
		world.init();
		
		//write results in file to compare beginning and end
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/aphanel/Documents/scripts/genes"), "utf-8"))) {				
			Double startingAverage = 0.0;
			Double endingAverage = 0.0;
			for (int i = 0; i < world.getChromosomePool().size(); i++) {
				writer.write(world.getChromosomePool().get(i).getFitnessScore().toString()+" ");
				startingAverage += world.getChromosomePool().get(i).getFitnessScore();
			}
			writer.write("Starting Average Fitness : "+startingAverage);
			writer.write("\n");
			
			//All the logic happens here
			world.run();
			
			for (int i = 0; i < world.getChromosomePool().size(); i++) {
				writer.write(world.getChromosomePool().get(i).getFitnessScore().toString()+" ");
				endingAverage += world.getChromosomePool().get(i).getFitnessScore();
			}
			writer.write("Ending Average Fitness : "+endingAverage);
		}
	}

}
