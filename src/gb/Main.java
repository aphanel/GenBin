package gb;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException, FileNotFoundException, IOException {
		//Instantiate and init the world
		World world = new World();
		world.init();
		
		//write results in file to compare beginning and end
		Integer first50 = 0;
		Integer second50 = 0;		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/aphanel/Documents/scripts/genes"), "utf-8"))) {
				
		
			for (int i = 0; i < world.getChromosomePool().size(); i++) {
				writer.write(world.getChromosomePool().get(i).getFinalFitnessScore().toString());
				if(world.getChromosomePool().get(i).getFinalFitnessScore().toString().equals("50.0")){
					first50++;
				}
			}
			writer.write(first50.toString());
			writer.write("\n");
			
			//All the logic happens here
			world.run();
			
			for (int i = 0; i < world.getChromosomePool().size(); i++) {
				writer.write(world.getChromosomePool().get(i).getFinalFitnessScore().toString());
				if(world.getChromosomePool().get(i).getFinalFitnessScore().toString().equals("50.0")){
					second50++;
				}
			}
			writer.write(second50.toString());
		}
	}

}
