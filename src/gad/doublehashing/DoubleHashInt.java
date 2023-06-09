package gad.doublehashing;

import java.nio.charset.StandardCharsets;

public class DoubleHashInt implements DoubleHashable<Integer> {

	int primeSize;
	int[] vector;
	public DoubleHashInt(int primeSize) {
		this.primeSize = primeSize;
	}

	@Override
	public int hash(Integer key) {
		return key % primeSize;
	}

	@Override
	public int hashTick(Integer key) {
		//return 1 + (key/primeSize) % (primeSize - 1);
		return (1 + key) % (primeSize - 1);
	}
}