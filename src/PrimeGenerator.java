/**
 * This class generates prime numbers up to a user-specified maximum. The
 * algorithm used is the Sieve of Eratosthenes.
 * <p>
 * Eratosthenes of Cyrene, b.c. 276 BC, Cyrene, Libya; d.c.194 BC,Alexandria. He
 * was the first man to calculate the circumference of the Earth, and was also
 * known for working on calendars with leap years and running the library at
 * Alexandria.
 * </p>
 * 
 * The algorithm is quite simple: Given an array of integers starting at 2,
 * cross out all multiples of 2. Find the next uncrossed integer, and cross out
 * all of its multiples. Repeat until you have passed the square root of the
 * maximum value.
 * 
 * @authorAlphonse, @version 13 Feb 2002 atp
 */

public class PrimeGenerator {
	private static boolean[] crossedOut;
	private static int[] result;

	public static int[] generatePrimes(int maxValue) {
		if (maxValue < 2)
			return new int[0];
		else {
			uncrossIntegersUpTo(maxValue);
			crossOutMultiples();
			putUncrossedIntegersIntoResult();
			return result;
		}
	}

	private static void uncrossIntegersUpTo(int maxValue) {
		crossedOut = new boolean[maxValue + 1];
		for (int i = 2; i < crossedOut.length; i++)
			crossedOut[i] = false;
	}

	private static void crossOutMultiples() {
		int maxPrimeFactor = determineIterationLimit();
		for (int i = 2; i < maxPrimeFactor; i++) {
			if (notCrossed(i))
				crossOutMultipleOf(i);
		}
	}

	private static void putUncrossedIntegersIntoResult() {
		result = new int[numberOfUncrossedIntegers()];
		for (int i = 2, j = 0; i < crossedOut.length; i++) {
			if (notCrossed(i))
				result[j++] = i;
		}
	}

	private static int determineIterationLimit() {
		// Every multiple in the array has a prime factor that is
		// less than or equal to the sqrt of the array size, so we
		// don't have to cross out multiples of numbers larger than that root.
		double iterationLimit = Math.sqrt(crossedOut.length);
		return (int) iterationLimit;
	}

	private static boolean notCrossed(int i) {
		return crossedOut[i] == false;
	}

	private static void crossOutMultipleOf(int i) {
		for (int multiple = 2 * i; multiple < crossedOut.length; multiple+=i)
			crossedOut[multiple] = true;
	}

	private static int numberOfUncrossedIntegers() {
		int count = 0;
		for (int i = 2; i < crossedOut.length; i++) {
			if (notCrossed(i))
				count++;
		}
		return count;
	}
}
