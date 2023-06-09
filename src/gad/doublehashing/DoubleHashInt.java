package gad.doublehashing;

import java.nio.charset.StandardCharsets;

public class DoubleHashInt implements DoubleHashable<Integer> {

	private int primeSize;
	private int[] a;
	private int[] b;

	public DoubleHashInt(int primeSize) {
		this.primeSize = primeSize;
		a = new int[] {1, 2, 3};
		b= new int[] {4, 5, 6};
	}

	@Override
	public int hash(Integer key) {
		byte[] x = bytes(key);
		int sum = 0;
		for (int i = 0; i < x.length; i++) {
			sum += x[i] * a[i % a.length];
		}
		return fastModulo(sum, primeSize);
	}

	@Override
	public int hashTick(Integer key) {
		byte[] x = bytes(key);
		int sum = 0;
		for (int i = 0; i < x.length; i++) {
			sum += x[i] * b[i % b.length];
		}
		return fastModulo(sum, primeSize);
	}

	private byte[] bytes(Integer key) {
		return key.toString().getBytes(StandardCharsets.UTF_8);
	}

	public static int fastModulo(int i, int divisor) {
		return i & (divisor - 1);
	}
}