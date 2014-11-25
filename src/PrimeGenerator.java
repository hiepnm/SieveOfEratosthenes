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
	private static int s;
	private static boolean[] f;
	private static int[] primes;

	public static int[] generatePrimes(int maxValue) {
		if (maxValue < 2)
			return new int[0];
		else {
			initializeSieve(maxValue);
			sieve();
			loadPrimes();
			return primes; // return the primes
		}
	}

	private static void loadPrimes() {
		int i, j;
		// how many primes are there?
		int count = 0;
		for (i = 0; i < s; i++) {
			if (f[i])
				count++; // bump count.
		}
		primes = new int[count];
		// move the primes into the result
		for (i = 0, j = 0; i < s; i++) {
			if (f[i]) // if prime
				primes[j++] = i;
		}
	}

	private static void sieve() {
		int i, j;
		for (i = 2; i < Math.sqrt(s) + 1; i++) {
			// if i is uncrossed, cross out its multiples.
			if (f[i]) {
				for (j = 2 * i; j < s; j += i)
					f[j] = false; // multiple is not prime
			}
		}
	}

	private static void initializeSieve(int maxValue) {
		// declarations
		s = maxValue + 1; // size of array
		f = new boolean[s];
		// initialize array to true.
		for (int i = 0; i < s; i++)
			f[i] = true;
		// get rid of known non-primes
		f[0] = f[1] = false;
	}
}