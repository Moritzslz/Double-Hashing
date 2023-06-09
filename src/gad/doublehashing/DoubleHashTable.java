package gad.doublehashing;

import java.util.Optional;

public class DoubleHashTable<K, V> {

	private int primeSize;
	private HashableFactory<K> hashableFactory;
	private Pair<K, V>[] pairs;
	private DoubleHashable<K> hashable;
	int collisions;
	int maxRehashes;

	@SuppressWarnings("unchecked")
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		// Erstellt ein Array von Pairs: (Pair<K, V>[]) new Pair[primeSize];
		this.primeSize = primeSize;
		this.hashableFactory = hashableFactory;
		this.hashable = hashableFactory.create(primeSize);
		pairs = new Pair[primeSize];
		collisions = 0;
		maxRehashes = 0;
	}

	public int hash(K key, int i) {
		if (i > maxRehashes)
			maxRehashes++;
		return hashable.hash(key) + i * hashable.hashTick(key);
	}

	public boolean insert(K k, V v) {
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i].one() == k) {
				if (pairs[i].two() == null) {
					pairs[i] = new Pair<>(k, v);
					return true;
				} else {
					collisions++;
					pairs[i] = new Pair<>(k, v);
					return true;
				}
			}
		}
		return false;
	}

	public Optional<V> find(K k) {
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i].one() == k) {
				return Optional.of(pairs[i].two());
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