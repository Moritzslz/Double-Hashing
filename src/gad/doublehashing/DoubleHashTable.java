package gad.doublehashing;

import java.util.Optional;

public class DoubleHashTable<K, V> {

	Pair<K, V>[] pairs;
	int primeSize;
	HashableFactory hashableFactory;
	DoubleHashable doubleHashable;
	int collisions;
	int maxRehashes;

	@SuppressWarnings("unchecked")
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		pairs = (Pair<K, V>[]) new Pair[primeSize];
		this.primeSize = primeSize;
		this.hashableFactory = hashableFactory;
		this.doubleHashable = hashableFactory.create(primeSize);
		collisions = 0;
		maxRehashes = 0;
	}

	public int hash(K key, int i) {
		return  (doubleHashable.hash(key) + i * doubleHashable.hashTick(key)) % primeSize;
	}

	public boolean insert(K k, V v) {
		int hash0 = hash(k, 0);
		int hash1 = hash(k, 1);
		int hash2 = hash(k, 2);
		int hash3 = hash(k, 3);
		if (pairs[hash0] == null) {
			//Initialize
			pairs[hash0] = new Pair<>(k, v);
			return true;
		} else if (pairs[hash0].one() == k) {
			//Overwrite
			pairs[hash0] = new Pair<>(k, v);
			return true;
		} else if (pairs[hash1].one() == k) {
			//Skip
			pairs[hash1] = new Pair<>(k, v);
			collisions++;
			if (maxRehashes < 1)
				maxRehashes = 1;
			return true;
		} else if (pairs[hash2].one() == k) {
			//Skip
			pairs[hash2] = new Pair<>(k, v);
			collisions++;
			if (maxRehashes < 2)
				maxRehashes = 2;
			return true;
		} else if (pairs[hash3].one() == k) {
			//Skip
			pairs[hash3] = new Pair<>(k, v);
			collisions++;
			if (maxRehashes < 3)
				maxRehashes = 3;
			return true;
		}
		return false;
	}

	public Optional<V> find(K k) {
		int hash0 = hash(k, 0);
		int hash1 = hash(k, 1);
		int hash2 = hash(k, 2);
		int hash3 = hash(k, 3);
		if (pairs[hash0] == null) {
			return Optional.empty();
		} else if (pairs[hash0].one() == k) {
			if (pairs[hash0].two() != null)
				return Optional.of(pairs[hash0].two());
			else
				return Optional.empty();
		} else if (pairs[hash1].one() == k) {
			if (pairs[hash1].two() != null)
				return Optional.of(pairs[hash1].two());
			else
				return Optional.empty();
		} else if (pairs[hash2].one() == k) {
			if (pairs[hash2].two() != null)
				return Optional.of(pairs[hash2].two());
			else
				return Optional.empty();
		} else if (pairs[hash3].one() == k) {
			if (pairs[hash3].two() != null)
				return Optional.of(pairs[hash3].two());
			else
				return Optional.empty();
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