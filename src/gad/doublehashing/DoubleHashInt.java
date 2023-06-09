package gad.doublehashing;

import java.nio.charset.StandardCharsets;

public class DoubleHashInt implements DoubleHashable<Integer> {

	int primeSize;
	int[] vector;
	public DoubleHashInt(int primeSize) {
		this.primeSize = primeSize;
		this.vector = new int[]{1,2,3,4,5,6,7,8,9};
	}

	@Override
	public int hash(Integer key) {
		return key % primeSize;
	}

	@Override
	public int hashTick(Integer key) {
		byte[] bytes = bytes(key);
		int sum = 0;
		for (int i = 0; i < bytes.length; i++) {
			sum += bytes[i] * vector[i % vector.length];
		}
		return sum % primeSize;
	}
	private byte[] bytes(Integer key) {
		return key.toString().getBytes(StandardCharsets.UTF_8);
	}
}