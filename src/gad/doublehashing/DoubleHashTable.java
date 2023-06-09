package gad.doublehashing;

import java.util.Optional;

public class DoubleHashTable<K, V> {

	Pair<K, V>[] pairs;
	int primeSize;
	HashableFactory hashableFactory;
	DoubleHashable doubleHashable;
	int collisions;

	@SuppressWarnings("unchecked")
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		// Erstellt ein Array von Pairs: (Pair<K, V>[]) new Pair[primeSize];
		pairs = (Pair<K, V>[]) new Pair[primeSize];
		this.primeSize = primeSize;
		this.hashableFactory = hashableFactory;
		this.doubleHashable = hashableFactory.create(primeSize);
	}

	public int hash(K key, int i) {
		return (doubleHashable.hash(key) + i * doubleHashable.hashTick(key)) % primeSize;
	}

	public boolean insert(K k, V v) {
		Pair nPair = new Pair(k, v);
		for (int i=0; i < pairs.length; i++) {
			if (pairs[i].one().equals(k)) {
				if (!pairs[i].two().equals(null)) {
					collisions++;
					pairs[i] = nPair;
					return false;
				} else {
					pairs[i] = nPair;
					return true;
				}
			}
		}
		return false;
	}

	public Optional<V> find(K k) {
		for (int i=0; i < pairs.length; i++) {
			if (pairs[i].one().equals(k)) {
				return Optional.of(pairs[i].two());
			}
		}
		return Optional.empty();
	}

	public int collisions() {
		return collisions;
	}

	public int maxRehashes() {
		return 0;
	}
}