/**
 * This class generates prime numbers up to a user-specified maximum.
 * The algorithm used is the Sieve of Eratosthenes.
 * <p>
 * Eratosthenes of Cyrene, b.c. 276 BC, Cyrene, Libya; d.c.194 BC,Alexandria.
 * He was the first man to calculate the circumference of the Earth,
 * and was also known for working on calendars with leap years and
 * running the library at Alexandria.</p>
 *
 * The algorithm is quite simple:
 * Given an array of integers starting at 2, cross out all multiples of 2.
 * Find the next uncrossed integer, and cross out all of its multiples.
 * Repeat until you have passed the square root of the maximum value.
 *
 * @authorAlphonse, @version 13 Feb 2002 atp
 */

public class PrimeGenerator {
	private static boolean[] f;
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

	private static void putUncrossedIntegersIntoResult() {
		int i, j;
		// how many primes are there?
		int count = 0;
		for (i = 0; i < f.length; i++) {
			if (f[i])
				count++; // bump count.
		}
		result = new int[count];
		// move the primes into the result
		for (i = 0, j = 0; i < f.length; i++) {
			if (f[i]) // if prime
				result[j++] = i;
		}
	}

	private static void crossOutMultiples() {
		int i, j;
		for (i = 2; i < Math.sqrt(f.length) + 1; i++) {
			// if i is uncrossed, cross out its multiples.
			if (f[i]) {
				for (j = 2 * i; j < f.length; j += i)
					f[j] = false; // multiple is not prime
			}
		}
	}

	private static void initializeArrayOfIntegers(int maxValue) {
		f = new boolean[maxValue + 1];
		f[0] = f[1] = false; // neither primes nor multiples.
		for (int i = 2; i < f.length; i++)
			f[i] = true;
	}
}