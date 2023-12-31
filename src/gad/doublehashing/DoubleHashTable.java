package gad.doublehashing;

import java.util.Optional;

public class DoubleHashTable<K, V> {

	private Pair<K, V>[] pairs;
	private int primeSize;
	private DoubleHashable doubleHashable;
	private int collisions;
	private int maxRehashes;

	@SuppressWarnings("unchecked")
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		pairs = (Pair<K, V>[]) new Pair[primeSize];
		this.primeSize = primeSize;
		this.doubleHashable = hashableFactory.create(primeSize);
		collisions = 0;
		maxRehashes = 0;
	}

	public int hash(K key, int i) {
		return  (doubleHashable.hash(key) + i * doubleHashable.hashTick(key)) % primeSize;
	}

	public boolean insert(K k, V v) {
		int index = hash(k, 0);
		int rehashes = 0;
		boolean collision = false;

		for (int i = 0; i < primeSize; i++) {
			if (pairs[index] != null && !pairs[index].one().equals(k)) {
				index = hash(k, ++rehashes);
				if (!collision) {
					collisions++;
					collision = true;
				}
			} else {
				pairs[index] = new Pair<>(k, v);
				if (rehashes > maxRehashes) {
					maxRehashes = rehashes;
				}
				return true;
			}
		}
		return false;
	}

	public Optional<V> find(K k) {
		int index = hash(k, 0);
		int rehashes = 0;

		for(int i = 0; i < primeSize; i++) {
			if (pairs[index] == null) {
				return Optional.empty();
			} else if (pairs[index].one().equals(k)) {
				return Optional.of(pairs[index].two());
			} else {
				index = hash(k, ++rehashes);
			}
		}

		return Optional.empty();
	}

	public int collisions() {
		return collisions;
	}

	public int maxRehashes() {
		return maxRehashes;
	}

}