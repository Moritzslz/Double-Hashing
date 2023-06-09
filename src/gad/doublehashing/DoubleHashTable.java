package gad.doublehashing;

import java.util.ArrayList;
import java.util.Optional;

public class DoubleHashTable<K, V> {

	Pair<K, V>[] pairs;
	int primeSize;
	HashableFactory hashableFactory;
	DoubleHashable doubleHashable;
	int collisions;
	int maxRehashes;
	ArrayList beenHashed;

	@SuppressWarnings("unchecked")
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		// Erstellt ein Array von Pairs: (Pair<K, V>[]) new Pair[primeSize];
		pairs = (Pair<K, V>[]) new Pair[primeSize];
		this.primeSize = primeSize;
		this.hashableFactory = hashableFactory;
		this.doubleHashable = hashableFactory.create(primeSize);
		collisions = 0;
		maxRehashes = 0;
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
			beenHashed.add(hashKey);
			int rehash = rehash(k);
			pairs[rehash] = nPair;
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
		return maxRehashes;
	}

	private int rehash (K k){
		int i = 1;
		int nHash = hash(k, i);
		collisions++;
		while (beenHashed.contains(nHash)) {
			i++;
			collisions++;
			nHash = hash(k, i);
		}
		if(i > maxRehashes)
			maxRehashes = i;
		return nHash;
	}
}