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
		int hashKey = hash(k,0);
		if (pairs[hashKey] == null) {
			pairs[hashKey] = nPair;
			return true;
		} else {
			pairs[hashKey] = nPair;
			collisions++;
		}
		return false;
	}

	public Optional<V> find(K k) {
		int hashKey = hash(k,0);
		if (pairs[hashKey] != null)
			return Optional.of(pairs[hashKey].two());
		return Optional.empty();
	}

	public int collisions() {
		return collisions;
	}

	public int maxRehashes() {
		return 0;
	}
}