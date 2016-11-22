package gb;

import java.util.ArrayList;

import java.util.Random;
//Stuff I found online because I was too lazy to do it myself. Thus not great regarding the problem at hands
public class Tools {

	public static double eval(final String str) {
	    return new Object() {
	        int pos = -1, ch;

	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        boolean eat(int charToEat) {
	            while (ch == ' ') nextChar();
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }

	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) return 0;
	            return x;
	        }

	        // Grammar:
	        // expression = term | expression `+` term | expression `-` term
	        // term = factor | term `*` factor | term `/` factor
	        // factor = `+` factor | `-` factor | `(` expression `)`
	        //        | number | functionName factor | factor `^` factor

	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); // addition
	                else if (eat('-')) x -= parseTerm(); // subtraction
	                else return x;
	            }
	        }

	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); // multiplication
	                else if (eat('/')) x /= parseFactor(); // division
	                else return x;
	            }
	        }

	        double parseFactor() {
	            if (eat('+')) return parseFactor(); // unary plus
	            if (eat('-')) return -parseFactor(); // unary minus

	            double x;
	            int startPos = this.pos;
	            if (eat('(')) { // parentheses
	                x = parseExpression();
	                eat(')');
	            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } else if (ch >= 'a' && ch <= 'z') { // functions
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                String func = str.substring(startPos, this.pos);
	                x = parseFactor();
	                if (func.equals("sqrt")) x = Math.sqrt(x);
	                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
//	                else throw new RuntimeException("Unknown function: " + func);
	                else return 0;
	            } else {
//	                throw new RuntimeException("Unexpected: " + (char)ch);
	            	return 0;
	            }

	            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	            return x;
	        }
	    }.parse();
	}
	
	public Integer rouletteSelect(ArrayList<Double> weight) {
		// Returns the selected index based on the weights(probabilities)
			// calculate the total weight
			Double weight_sum = 0.000000;
			for(int i=0; i<weight.size(); i++) {
				weight_sum += weight.get(i);
			}
			// get a random value
			Double value = randUniformPositive() * weight_sum;	
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
			// easiest implementation
			return new Random().nextDouble();
	}
}
