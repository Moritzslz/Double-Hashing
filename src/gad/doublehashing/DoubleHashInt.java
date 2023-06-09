package gad.doublehashing;

import java.nio.charset.StandardCharsets;

public class DoubleHashInt implements DoubleHashable<Integer> {

	int primeSize;
	public DoubleHashInt(int primeSize) {
		this.primeSize = primeSize;
	}

	@Override
	public int hash(Integer key) {
		byte[] bytes = bytes(key);
		String hashedString = "";
		for (int i = 0; i < bytes.length; i++) {
			if (i % 2 == 0)
				hashedString += ((bytes[i] + 3) * (i + 1)) % 16;
			else
				hashedString += (bytes[i] + 6) * (i - 1) % 7;
		}
		int hashedKey =  Integer.parseInt(hashedString);
		return hashedKey % primeSize;
	}

	@Override
	public int hashTick(Integer key) {
		byte[] bytes = bytes(key);
		String hashedString = "";
		for (int i = 0; i < bytes.length; i++) {
			if (i % 2 == 0)
				hashedString += ((bytes[i] + 9) * (i + 3)) % 26;
			else
				hashedString += (bytes[i] + 5) * (i + 2) % 13;
		}
		int hashedKey =  Integer.parseInt(hashedString);
		return hashedKey & primeSize;
	}
	private byte[] bytes(Integer key) {
		return key.toString().getBytes(StandardCharsets.UTF_8);
	}
}