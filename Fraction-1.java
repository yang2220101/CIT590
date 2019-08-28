package hw7;

public class Fraction {

	int numerator;
	int denominator;
	int sign;

	/**
	 * Constructor
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(int numerator, int denominator) {
		//if the fraction is negative, set sign to -1
		if(numerator * denominator < 0) {
			this.sign = -1;
		}
		//if the fraction is negative,set sign to 1
		if(numerator * denominator > 0) {
			this.sign = 1;
		}
		//if the fraction is zero,set sign to 0
		if(numerator == 0) {
			this.sign = 0;
		}
		//format convention
		this.numerator = this.sign * Math.abs(numerator);
		this.denominator = Math.abs(denominator);
	}

	/**
	 * Get the greatest common divisor of numerator and denominator.
	 * @param greater
	 * @param n
	 * @return
	 */
	public static int greatestCommonDivisor(int greater, int smaller) {
		//the case that at least one parameter is 0
		if(greater == 0) {
			return smaller;
		}else if(smaller == 0) {
			return greater;
		}else {
			//find gcd
			if (greater < smaller) {
				int swap = greater;
				greater = smaller;
				smaller = swap;
			}
			while (greater % smaller != 0) {
				int swap = greater % smaller;
				greater = smaller;
				smaller = swap;
			}
			return smaller;
		}
	}

	/**
	 * Get the least common multiple of numerator and denominator.
	 * @param m
	 * @param n
	 * @return
	 */
	public static int leastCommonMultiple(int greater, int smaller) {
		//find lcm
		if(greater * smaller == 0) {
			return 0;
		}else {
			return greater * smaller / greatestCommonDivisor(greater, smaller);
		}
	}


	/**
	 * Reduce the current fraction by eliminating common factors.
	 */
	public void reduceToLowestForm() {
		//when the fraction is 0
		if(this.sign == 0) {
			this.numerator = 0;
			this.denominator = 1;
		}else{
			//reduce to lowest form with gcd
			int gcd = greatestCommonDivisor(this.numerator, this.denominator);
			this.numerator /= gcd;
			this.denominator /= gcd;
		}
		this.denominator = Math.abs(this.denominator);
		this.numerator = this.sign * Math.abs(this.numerator);
	}

	/**
	 * Add the current fraction to the given otherFraction.
	 * @param otherFraction
	 * @return
	 */
	public Fraction add(Fraction otherFraction) {
		//reduction of fractions to a common denominator
		int lcm = leastCommonMultiple(this.denominator, otherFraction.denominator);
		//add two numerators
		int sumNumerator = this.numerator * lcm / this.denominator + otherFraction.numerator * lcm / otherFraction.denominator;
		//store the sum as a new fraction
		Fraction sum = new Fraction(sumNumerator, lcm);
		sum.reduceToLowestForm();
		return sum;

	}

	/**
	 * Subtract the given otherFraction from the current fraction.
	 * @param otherFraction
	 * @return
	 */
	public Fraction subtract(Fraction otherFraction) {
		//find the opposite number of otherFraction
		Fraction subtraction = new Fraction(otherFraction.numerator * -1, otherFraction.denominator);
		//add opposite value
		Fraction dif = this.add(subtraction);
		return dif;
	}

	/**
	 * Multiply the current fraction by the given otherFraction.
	 * @param otherFraction
	 * @return
	 */
	public Fraction mul(Fraction otherFraction) {
		//multiplication for numerators and denominators
		int newNumerator = this.numerator * otherFraction.numerator;
		int newDenominator = this.denominator * otherFraction.denominator;
		//store the result as a new fraction
		Fraction mul = new Fraction(newNumerator, newDenominator);
		mul.reduceToLowestForm();
		return mul;
	}

	/**
	 * Divide the current fraction by the given otherFraction.
	 * @param otherFraction
	 * @return
	 */
	public Fraction div(Fraction otherFraction) {
		//to test if the divisor is 0
		if(otherFraction.numerator == 0) {
			throw new ArithmeticException("Divisor cannot be 0!");	
		}
		//find the reciprocal of otherFraction
		Fraction division = new Fraction(otherFraction.denominator * otherFraction.sign, Math.abs(otherFraction.numerator));
		//multiply with the reciprocal of otherFraction
		Fraction div = this.mul(division);
		div.reduceToLowestForm();
		return div;
	}

	/**
	 * Return this fraction in decimal form.
	 * @return
	 */
	public double decimal() {
		//transform fraction into decimal
		double numerator = this.numerator;
		double denominator = this.denominator;
		double dec =  numerator / denominator;
		return dec;
	}

	/**
	 * Square the current fraction.
	 */
	public void sqr() {
		//Square the numerator and denominator of current fraction
		this.numerator = this.numerator * this.numerator;
		this.denominator = this.denominator * this.denominator;
	}

	/**
	 * Average the current fraction with the given otherFraction.
	 * @param otherFraction
	 * @return
	 */
	public Fraction average(Fraction otherFraction) {
		//add two fractions, then divide by 2
		Fraction ave = this.add(otherFraction);
		Fraction two = new Fraction(2, 1);
		ave = ave.div(two);
		ave.reduceToLowestForm();
		return ave;
	}

	/**
	 * Static method to average all of the fractions in the given array.
	 * @param fractions
	 * @return
	 */
	public static Fraction average(Fraction[] fractions) {
		//the case of empty array
		Fraction sum = new Fraction(0,1);
		if(fractions.length == 0) {
			return sum;
		}else {
			//add all the fractions, then divide by length of array
			for(int i=0; i < fractions.length; i++) {
				sum = sum.add(fractions[i]);
			}
		}
		Fraction len = new Fraction(fractions.length, 1);
		Fraction ave = sum.div(len);
		ave.reduceToLowestForm();
		return ave;
	}

	/**
	 * Static method to average all the integers in the given array.
	 * @param ints
	 * @return
	 */
	public static Fraction average(int[] ints) {
		//the case of empty array
		int num = 0;
		if(ints.length == 0) {
			return new Fraction(0,1);
		}else {
			//add all the integers, then divide by length of array
			for(int i=0; i < ints.length; i++) {
				num += ints[i];
			}
			Fraction ave = new Fraction(num, ints.length);
			ave.reduceToLowestForm();
			return ave;
		}
	}

	/**
	 * Return a string representation of the current fraction.
	 */
	public String toString() {
		//Return a string representation of the current fraction
		return this.numerator + "/" + this.denominator;
	}
	public static void main(String[] args) {
		/*
		Fraction fraction1 = new Fraction(32,16);
		Fraction fraction2 = new Fraction(8,16);
		System.out.println(fraction2.numerator);
		System.out.println(fraction2.denominator);
		System.out.println(fraction1.decimal());
		System.out.println(fraction2.decimal());
		 */
	}

}
