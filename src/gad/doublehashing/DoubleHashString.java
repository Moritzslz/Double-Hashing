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
		int hash = 0;
		for (char c : key.toCharArray())
			hash += c;
		return hash % primeSize;
	}

	@Override
	public int hashTick(String key) {
		int hash = 0;
		for (char c : key.toCharArray())
			hash += c;
		return  1 + hash % (primeSize - 1);
	}
}