package gad.doublehashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DoubleHashInt implements DoubleHashable<Integer> {

	private int primeSize;

	public DoubleHashInt(int primeSize) {
		this.primeSize = primeSize;
	}

	@Override
	public int hash(Integer key) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] hash = digest.digest(bytes(key));
		int hashKey = bytesToInt(hash);
		return hashKey;
	}

	@Override
	public int hashTick(Integer key) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] hash = digest.digest(bytes(key));
		int hashKey = bytesToInt(hash);
		return hashKey;
	}

	public int bytesToInt(byte[] hash) {
		int result = 0;
		for (int i = 0; i < this.primeSize; i++) {
			result |= (hash[i] & 0xFF) << (8 * (this.primeSize - i - 1));
		}
		return result;
	}

	private byte[] bytes(Integer key) {
		return key.toString().getBytes(StandardCharsets.UTF_8);
	}
}