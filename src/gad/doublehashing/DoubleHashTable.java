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
		int hashKey = (doubleHashable.hash(key) + i * doubleHashable.hashTick(key)) % primeSize;
		return hashKey;
	}

	public boolean insert(K k, V v) {
		Pair nPair = new Pair(k, v);
		int hashKey = hash(k,0);

		if (pairs[hashKey] == null) {
			//Initialize Field
			pairs[hashKey] = nPair;
			return true;
		} else if (pairs[hashKey].one() == k) {
			//Overwrite
			pairs[hashKey] = nPair;
			return true;
		} else if (isFull())
			return false;
		else if (pairs[hashKey].one() != k) {
			//Collision
			hashKey = hash(k, 1);
			pairs[hashKey] = nPair;
			return true;
		}
		return false;
	}

	public Optional<V> find(K k) {
		int hashKey = hash(k,0);
		if (pairs[hashKey].two() != null)
			return Optional.of(pairs[hashKey].two());
		return Optional.empty();
	}

	public int collisions() {
		return collisions;
	}

	public int maxRehashes() {
		return maxRehashes;
	}

	private int rehash (K k, int i) {
		int rehash = hash(k, i);
		if (pairs[rehash] == null || pairs[rehash].one() == k) {
			if (i > maxRehashes)
				maxRehashes = i;
			return rehash;
		} else
			return rehash(k, i+1);
	}

	private boolean isFull() {
		for (int i = 0; i < pairs.length; i++) {
			if (pairs[i] == null)
				return false;
		}
		return true;
	}

}