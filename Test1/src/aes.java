

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class aes {

// test commit
	public static void main(String[] args)  throws Exception{

		String jwe= "ff3e310d6892d7f547b9c72d20b3de9d7ff1318c0301697cdddfcf5a8f11d84f709308845b29e6eab92c4da6d7e5561f66877d8ed2cf403851f67e8a9b8d9faee0845737494a30b2b2a6ea6d6e10f491690e82d826cca98680ae69b6c0469567ea0682477778c988d8c3417ae2314f00";
		String jweme ="D6F35CDAB638457DED063F5F56B6C7338E23FE5103647F2E17A6C79DB9F580C4FD85E1155F75E5484924B97A3135D60909B8578A205F201A676406DC023492EAE82F01C6E3A2121C995BD1CEF32F5B3089BF764315FA1D9FD6E090B656375CF802A813C6EE13571F9F1D2490B704A9C3";
		System.out.println(decAES(jweme));

	}




	public static Key getAESKey() throws Exception {
	    String iv;
	    Key keySpec;

	    String key = "3702144a52b39a1c";
	    iv = key.substring(0, 16);
	    byte[] keyBytes = new byte[16];
	    byte[] b = key.getBytes("UTF-8");

	    int len = b.length;
	    if (len > keyBytes.length) {
	       len = keyBytes.length;
	    }

	    System.arraycopy(b, 0, keyBytes, 0, len);
	    keySpec = new SecretKeySpec(keyBytes, "AES");

	    return keySpec;
	}

	// �部嶸ｸ嶹�
	public String encAES(String str) throws Exception {
	    Key keySpec = getAESKey();
	    String iv = "11fb428b40e682de92b2c02f6dc6e9fe";
	    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    byte[] bytes = new java.math.BigInteger(iv, 16).toByteArray();
	    c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(bytes));
	    byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
	    String enStr = new String( Base64.getEncoder().encode(encrypted));

	    return enStr;
	}

	// �ｳｵ嶸ｸ嶹�
	public static String decAES(String enStr) throws Exception {
	    Key keySpec = getAESKey();
	    String iv = "11fb428b40e682de92b2c02f6dc6e9fe";
	    byte[] bytes = new java.math.BigInteger(iv, 16).toByteArray();
	    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(bytes));
	    byte[] byteStr = enStr.getBytes("UTF-8");

	    String decStr = new String(c.doFinal(byteStr), "UTF-8");

	    return decStr;
	}

}
