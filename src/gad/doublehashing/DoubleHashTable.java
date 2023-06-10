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
		int i = 0;
		Pair nPair = new Pair(k, v);
		int hashKey = hash(k,i);

		if (pairs[hashKey] == null) {
			//Initialize Field
			pairs[hashKey] = nPair;
			return true;
		}
		if (pairs[hashKey].one() == k) {
			//Overwrite
			pairs[hashKey] = nPair;
			return true;
		} else if(pairs[hashKey].one() != k) {
			while (pairs[hashKey].one() != k && pairs[hashKey] != null) {
				//Collision
				i++;
				collisions++;
				hashKey = hash(k, i);
			}
			if (i > maxRehashes)
				maxRehashes = i;
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

	private int rehash (K k) {
		return 0;
	}

}