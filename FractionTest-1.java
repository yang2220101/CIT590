package hw7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FractionTest {

	Fraction fraction1;
	Fraction fraction2;
	Fraction fraction3;
	Fraction fraction4;
	Fraction fraction5;
	Fraction[] fractions1 = {new Fraction(3, 4), new Fraction(3, 5), new Fraction(3, 6)};
	Fraction[] fractions2 = {new Fraction(3, 4), new Fraction(-3, 5), new Fraction(3, 6), new Fraction(0, 1)};
	Fraction[] fractions3 = {};
	int[] ints1 = {1, 2, 3, 4};
	int[] ints2 = {1, -2, 3, -4, 0};
	int[] ints3 = {};
	double delta;


	@BeforeEach
	void setUp() throws Exception {
		this.fraction1 = new Fraction(4,16);
		this.fraction2 = new Fraction(-4,16);
		this.fraction3 = new Fraction(-1,-2);
		this.fraction4 = new Fraction(10,-15);
		this.fraction5 = new Fraction(0,4);
		this.delta = 0.000001;
	}

	@Test
	void testFraction() {
		//test constructor
		assertEquals(16, fraction1.denominator);
		assertEquals(4, fraction1.numerator);
		assertEquals(16, fraction2.denominator);
		assertEquals(-4, fraction2.numerator);
		assertEquals(16, fraction2.denominator);
		assertEquals(1, fraction3.numerator);
		assertEquals(2, fraction3.denominator);
		assertEquals(-10, fraction4.numerator);
		assertEquals(15, fraction4.denominator);
		assertEquals(0, fraction5.numerator);
		assertEquals(4, fraction5.denominator);
	}

	@Test
	void testGreatestCommonDivisor() {
		//test the method to get gcd
		assertEquals(1, Fraction.greatestCommonDivisor(8, 1));
		assertEquals(8, Fraction.greatestCommonDivisor(8, 16));
		assertEquals(3, Fraction.greatestCommonDivisor(9, 6));
		assertEquals(8, Fraction.greatestCommonDivisor(8, 0));
		assertEquals(8, Fraction.greatestCommonDivisor(0, 8));
		assertEquals(0, Fraction.greatestCommonDivisor(0, 0));
	}

	@Test
	void testLeastCommonMultiple() {
		//test the method to get lcm
		assertEquals(8, Fraction.leastCommonMultiple(8, 1));
		assertEquals(16, Fraction.leastCommonMultiple(8, 16));
		assertEquals(18, Fraction.leastCommonMultiple(9, 6));
		assertEquals(0, Fraction.leastCommonMultiple(8, 0));
		assertEquals(0, Fraction.leastCommonMultiple(0, 8));
		assertEquals(0 ,Fraction.leastCommonMultiple(0, 0));
	}

	@Test
	void testReduceToLowestForm() {
		//4/16 to 1/4(normal)
		fraction1.reduceToLowestForm();
		assertEquals(4, fraction1.denominator);
		assertEquals(1, fraction1.numerator);
		//10/-15 to -2/3(negative denominator)
		fraction4.reduceToLowestForm();
		assertEquals(3,fraction4.denominator);
		assertEquals(-2, fraction4.numerator);
		//0/4 to 0/1(zero)
		fraction5.reduceToLowestForm();
		assertEquals(1, fraction5.denominator);
		assertEquals(0, fraction5.numerator);

	}

	@Test
	void testAdd() {
		//4/16 + (-4/16) = 0/1(negative numerator)
		assertEquals(0, fraction1.add(fraction2).numerator);
		assertEquals(1, fraction1.add(fraction2).denominator);
		//4/16 + (10/-15) = -5/12(negative denominator)
		assertEquals(-5, fraction1.add(fraction4).numerator);
		assertEquals(12, fraction1.add(fraction4).denominator);
		//4/16 + 0/4 = 1/4(zero)
		assertEquals(1, fraction1.add(fraction5).numerator);
		assertEquals(4, fraction1.add(fraction5).denominator);
		//4/16 + 1/2 = 3/4(normal)
		assertEquals(3, fraction1.add(fraction3).numerator);
		assertEquals(4, fraction1.add(fraction3).denominator);
	}

	@Test
	void testSubtract() {
		//-4/16 -(4/16) = -1/2(normal)
		assertEquals(-1, fraction2.subtract(fraction1).numerator);
		assertEquals(2, fraction2.subtract(fraction1).denominator);
		//4/16 - (10/-15) = 11/12(negative denominator)
		assertEquals(11, fraction1.subtract(fraction4).numerator);
		assertEquals(12, fraction1.subtract(fraction4).denominator);
		//4/16 - 0/4 = 1/4(zero)
		assertEquals(1, fraction1.subtract(fraction5).numerator);
		assertEquals(4, fraction1.subtract(fraction5).denominator);
		//4/16 -(4/16) = 0/1(self)
		assertEquals(0, fraction1.subtract(fraction1).numerator);
		assertEquals(1, fraction1.subtract(fraction1).denominator);
	}

	@Test
	void testMul() {
		//4/16 * (10/-15) = -1/6(negative denominator)
		assertEquals(-1, fraction1.mul(fraction4).numerator);
		assertEquals(6, fraction1.mul(fraction4).denominator);
		//4/16 * 1/2 = 1/8(normal)
		assertEquals(1, fraction1.mul(fraction3).numerator);
		assertEquals(8, fraction1.mul(fraction3).denominator);
		//4/16 * 0/4 = 0/1(zero)
		assertEquals(0, fraction1.mul(fraction5).numerator);
		assertEquals(1, fraction1.mul(fraction5).denominator);
	}

	@Test
	void testDiv() {
		//4/16 / (-4/16) = -1/1(negative numerator)
		assertEquals(-1, fraction1.div(fraction2).numerator);
		assertEquals(1, fraction1.div(fraction2).denominator);
		//4/16 / 1/2 = 1/2(normal)
		assertEquals(1, fraction1.div(fraction3).numerator);
		assertEquals(2, fraction1.div(fraction3).denominator);
		//0/4 / (10/-15) = 0/1(zero)
		assertEquals(0, fraction5.div(fraction4).numerator);
		assertEquals(1, fraction5.div(fraction4).denominator);
		//4/16 / 0/4 will throw exception(0 as divisor)
		assertThrows(ArithmeticException.class, () -> {
			fraction1.div(fraction5);
		});
	}

	@Test
	void testDecimal() {
		//4/16 = 0.25(normal)
		assertEquals(0.25, fraction1.decimal(), delta);
		//0/4 = 0.0(zero)
		assertEquals(0.0, fraction5.decimal(), delta);
		//10/-15 = 0.66666666...(negative)
		assertEquals(-2.0/3.0, fraction4.decimal(), delta);
	}

	@Test
	void testSqr() {
		//(4/16)^2 = 16/156(normal)
		fraction1.sqr();
		assertEquals(16, fraction1.numerator);
		assertEquals(256, fraction1.denominator);
		//(-10/15)^2 = 100/225(negative)
		fraction4.sqr();
		assertEquals(100, fraction4.numerator);
		assertEquals(225, fraction4.denominator);
		//(0/4)^2 = 0/1(zero)
		fraction5.sqr();
		assertEquals(0, fraction5.numerator);
		assertEquals(16, fraction5.denominator);
		//(1/2)^2 = 1/4(lowest form)
		fraction3.sqr();
		assertEquals(1, fraction3.numerator);
		assertEquals(4, fraction3.denominator);
	}

	@Test
	void testAverageFraction() {
		//(4/16 + (4/16))/2 = 1/4(normal)
		assertEquals(1, fraction1.average(fraction1).numerator);
		assertEquals(4, fraction1.average(fraction1).denominator);
		//(4/16 + (10/-15))/2 = -5/24(negative denominator)
		assertEquals(-5, fraction1.average(fraction4).numerator);
		assertEquals(24, fraction1.average(fraction4).denominator);
		//(4/16 + 0/4)/2 = 1/8(zero)
		assertEquals(1, fraction1.average(fraction5).numerator);
		assertEquals(8, fraction1.average(fraction5).denominator);
	}

	@Test
	void testAverageFractionArray() {
		//average of fractions1(normal)
		assertEquals(37, Fraction.average(fractions1).numerator);
		assertEquals(60, Fraction.average(fractions1).denominator);
		//average of fractions2(zero and negative)
		assertEquals(13, Fraction.average(fractions2).numerator);
		assertEquals(80, Fraction.average(fractions2).denominator);
		//average of fractions3(empty array)
		assertEquals(0, Fraction.average(fractions3).numerator);
		assertEquals(1, Fraction.average(fractions3).denominator);
	}

	@Test
	void testAverageIntArray() {
		//average of fractions1(normal)
		assertEquals(5, Fraction.average(ints1).numerator);
		assertEquals(2, Fraction.average(ints1).denominator);
		//average of fractions2(zero and negative)
		assertEquals(-2, Fraction.average(ints2).numerator);
		assertEquals(5, Fraction.average(ints2).denominator);
		//average of fractions3(empty array)
		assertEquals(0, Fraction.average(ints3).numerator);
		assertEquals(1, Fraction.average(ints3).denominator);
	}

	@Test
	void testToString() {
		//(normal)
		assertEquals("4/16", fraction1.toString());
		//(negative numerator)
		assertEquals("-4/16", fraction2.toString());
		//(lowest form)
		assertEquals("1/2", fraction3.toString());
		//(negative denominator)
		assertEquals("-10/15", fraction4.toString());
		//(zero)
		assertEquals("0/4", fraction5.toString());
	}


}
