package gad.doublehashing;

import java.util.Optional;

public class DoubleHashTable<K, V> {

	Pair<K, V>[] pairs;
	int primeSize;
	HashableFactory hashableFactory;
	DoubleHashable doubleHashable;
	int collisions;
	int maxRehashes;
	int numberOfElements;

	@SuppressWarnings("unchecked")
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		pairs = (Pair<K, V>[]) new Pair[primeSize];
		this.primeSize = primeSize;
		this.hashableFactory = hashableFactory;
		this.doubleHashable = hashableFactory.create(primeSize);
		collisions = 0;
		maxRehashes = 0;
		numberOfElements = 0;
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
				if (rehashes > maxRehashes)
					maxRehashes = rehashes;
				return true;
			}
		}
		return false;
	}

	public Optional<V> find(K k) {
		int index = hash(k, 0);
		int rehashes = 0;

		while (pairs[index] != null) {
			if (pairs[index].one().equals(k)) {
				return Optional.of(pairs[index].two());
			} else {
				index = hash(k, ++rehashes);
			}
		}
		/*
		for(int i = 0; i < primeSize; i++) {
			if (pairs[index] == null)
				return Optional.empty();
			else if (!pairs[index].one().equals(k)) {
				index = hash(k, ++rehashes);
			} else {
				return Optional.of(pairs[index].two());
			}
		}

		 */

		return Optional.empty();
	}

	public int collisions() {
		return collisions;
	}

	public int maxRehashes() {
		return maxRehashes;
	}

}