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
	private static boolean[] isCrossed;
	private static int[] result;

	public static int[] generatePrimes(int maxValue) {
		if (maxValue < 2)
			return new int[0];
		else {
			initializeArrayOfIntegers(maxValue);
			crossOutMultiples();
			putUncrossedIntegersIntoResult();
			return result; // return the primes
		}
	}

	private static void initializeArrayOfIntegers(int maxValue) {
		isCrossed = new boolean[maxValue + 1];
		for (int i = 2; i < isCrossed.length; i++)
			isCrossed[i] = false;
	}

	private static void crossOutMultiples() {
		int maxPrimeFactor = calcMaxPrimeFactor();
		for (int i = 2; i < maxPrimeFactor; i++) {
			if (notCrossed(i))
				crossOutMultipleOf(i);
		}
	}

	private static void putUncrossedIntegersIntoResult() {
		int i, j;
		// how many primes are there?
		int count = 0;
		for (i = 2; i < isCrossed.length; i++) {
			if (notCrossed(i))
				count++; // bump count.
		}
		result = new int[count];
		// move the primes into the result
		for (i = 2, j = 0; i < isCrossed.length; i++) {
			if (notCrossed(i)) // if prime
				result[j++] = i;
		}
	}

	private static int calcMaxPrimeFactor() {
		// We cross out all multiples of primes. Thus, all crossed
		// out multiples have p and q for factors. If p > sqrt of the
		// size of the array, then q will never be greater than 1.
		// Thus p is the largest prime factor in the array, and is
		// also the iteration limit.
		double maxPrimeFactor = Math.sqrt(isCrossed.length) + 1;
		return (int) maxPrimeFactor;
	}

	private static boolean notCrossed(int i) {
		return isCrossed[i] == false;
	}

	private static void crossOutMultipleOf(int i) {
		for (int multiple = 2 * i; multiple < isCrossed.length; multiple+=i)
			isCrossed[multiple] = true;
	}

}
