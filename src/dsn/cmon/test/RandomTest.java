package dsn.cmon.test;

import java.util.Random;

public class RandomTest {

	public RandomTest() {
		// TODO Auto-generated constructor stub
	}

	private Random fRandom = new Random();

	private double getGaussian(double aMean, double aVariance) {
		return aMean + fRandom.nextGaussian() * aVariance;
	}

	private static void log(Object aMsg) {
		System.out.println(String.valueOf(aMsg));
	}
	
	public static void main(String[] args) {
		RandomTest gaussian = new RandomTest();
		double MEAN = 10000000.0f;
		double VARIANCE = 5.0f;
		for (int idx = 1; idx <= 10; ++idx) {
			log("Generated : " + gaussian.getGaussian(MEAN, VARIANCE));
		}
	}

}
