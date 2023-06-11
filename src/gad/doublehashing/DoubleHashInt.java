package gad.doublehashing;

public class DoubleHashInt implements DoubleHashable<Integer> {

	private int primeSize;
	public DoubleHashInt(int primeSize) {
		this.primeSize = primeSize;
	}

	@Override
	public int hash(Integer key) {
		if(key < 0 ) {
			key *= -1;
		}
		return key % primeSize;
	}

	@Override
	public int hashTick(Integer key) {
		if(key < 0 ) {
			key *= -1;
		}
		return 1 + key % (primeSize - 1);
	}
}