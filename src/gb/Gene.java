package gb;

import java.util.Random;

public class Gene {
	
	private String code;
	private Random random;
	private Integer bit;
	
	public Gene(){
		this.bit = new Integer(0000);
		this.random = new Random();
		this.setRandomGene();
	}
	
	public String decode(){
		String decodedValue = null;
		switch (code) {
		case "0000":
			decodedValue = "0";
			break;
		case "0001":
			decodedValue = "1";			
			break;
		case "0010":
			decodedValue = "2";			
			break;
		case "0011":
			decodedValue = "3";			
			break;
		case "0100":
			decodedValue = "4";			
			break;
		case "0101":
			decodedValue = "5";			
			break;
		case "0110":
			decodedValue = "6";			
			break;
		case "0111":
			decodedValue = "7";			
			break;
		case "1000":
			decodedValue = "8";			
			break;
		case "1001":
			decodedValue = "9";			
			break;
		case "1010":
			decodedValue = "+";			
			break;
		case "1011":
			decodedValue = "-";			
			break;
		case "1100":
			decodedValue = "*";			
			break;
		case "1101":
			decodedValue = "/";			
			break;
		default:
			decodedValue = "N/A";
			break;
		}
		return decodedValue;		
	}
	
	//useless for now
	public Integer returnRandomGene(){
		bit = Integer.valueOf(Integer.toBinaryString(random.nextInt(14)));
		StringBuffer sbit = new StringBuffer();
		sbit.append(bit.toString());
		if(sbit.length()==1)sbit.insert(0, "000");
		else if(sbit.length()==2)sbit.insert(0, "00");
		else if(sbit.length()==3)sbit.insert(0, "0");
		bit = Integer.valueOf(sbit.toString());
		return bit;
	}
	
	public void setRandomGene(){
		bit = Integer.valueOf(Integer.toBinaryString(random.nextInt(14)));
		StringBuffer sbit = new StringBuffer();
		sbit.append(bit.toString());
		if(sbit.length()==1)sbit.insert(0, "000");
		else if(sbit.length()==2)sbit.insert(0, "00");
		else if(sbit.length()==3)sbit.insert(0, "0");
		code = sbit.toString();
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Random getRandom() {
		return random;
	}


	public void setRandom(Random random) {
		this.random = random;
	}
	
}
