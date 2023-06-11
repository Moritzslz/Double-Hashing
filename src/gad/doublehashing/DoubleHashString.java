package gad.doublehashing;

public class DoubleHashString implements DoubleHashable<String> {

	private int primeSize;
	public DoubleHashString(int primeSize) {
		this.primeSize = primeSize;
	}

	@Override
	public int hash(String key) {
		int hash = 0;
		for (char c : key.toCharArray()) {
			hash += c;
		}
		return hash % primeSize;
	}

	@Override
	public int hashTick(String key) {
		int hash = 0;
		for (char c : key.toCharArray()) {
			hash += c;
		}
		return  1 + hash % (primeSize - 1);
	}
}