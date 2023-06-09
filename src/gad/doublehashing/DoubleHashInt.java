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
		return key.hashCode();
		/*
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		int i = key.hashCode();
		byte[] hash = key.byteValue();
		return bytesToInt(hash);
		 */
	}

	@Override
	public int hashTick(Integer key) {
		return key.hashCode();
		/*
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] hash = key.byteValue();
		return bytesToInt(hash);
		 */
	}

	public int bytesToInt(byte[] hash) {
		int result = 0;
		for (int i = 0; i < this.primeSize; i++) {
			result |= (hash[i] & 0xFF) << (8 * (this.primeSize - i - 1));
		}
		return result;
	}
}