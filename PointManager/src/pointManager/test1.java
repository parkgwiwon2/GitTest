package pointManager;

public class test1 {

	public static void main(String[] args) {
		System.out.println(bytArrayToHex(toByteArray(376)));

	}

	public static byte[] toByteArray(long l) {
		byte[] bytes = new byte[]{(byte)(l >> 56), (byte)(l >> 48), (byte)(l >> 40), (byte)(l >> 32), (byte)(l >> 24), (byte)(l >> 16), (byte)(l >> 8), (byte)l};
		return bytes;
	}


	public static String bytArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for(final byte b: a)
			sb.append(String.format("%02x ", b&0xff));
		return sb.toString();
	}

}
