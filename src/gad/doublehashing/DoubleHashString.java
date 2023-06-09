package gad.doublehashing;

import java.nio.charset.StandardCharsets;

public class DoubleHashString implements DoubleHashable<String> {

	int primeSize;
	int[] vector;
	public DoubleHashString(int primeSize) {
		this.primeSize = primeSize;
		this.vector = new int[]{primeSize+420, primeSize+1, primeSize, primeSize+7, primeSize+69, primeSize+31};
	}

	@Override
	public int hash(String key) {
		byte[] bytes = bytes(key);
		int sum = 0;
		for (int i = 0; i < key.length(); i++) {
			sum += bytes[i] * vector[i % vector.length];
		}
		return sum % primeSize;
	}

	@Override
	public int hashTick(String key) {
		byte[] bytes = bytes(key);
		int sum = 0;
		for (int i = 0; i < key.length(); i++) {
			sum += bytes[i] * vector[i % vector.length];
		}
		return  1 + sum % (primeSize - 1);
	}

	private byte[] bytes(String key) {return key.getBytes(StandardCharsets.UTF_8);}
}