package modernwave_Utility;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class CryptServiceGw {
	public static byte[] KEY = "callquest^&!KOTRA_!@#^$}></-]STG".getBytes();

	static {
		Security.insertProviderAt(new BouncyCastleProvider(), 1);
	}

	public static void SetKey(byte[] cwKEY) {
		System.out.println("Callgate Key Changed..");
		KEY = cwKEY;
	}

	public void crypt(String value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");

		Cipher cipher = Cipher.getInstance("AES/ECB/ISO7816-4Padding");
		// Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
		System.out.println(String.valueOf(encrypted) + "        " + new String(KEY, "UTF-8"));

		// Cipher cipher2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] decrypted = cipher.doFinal(encrypted);

		System.out.println(new String(decrypted, "UTF-8"));

	}

	public static String EncryptString(String value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
		// Cipher cipher = Cipher.getInstance("AES/ECB/ISO7816-4Padding");
		Cipher cipher = Cipher.getInstance("AES/ECB/ISO7816-4Padding");
		System.out.println(String.format("blocksize >> %d", cipher.getBlockSize()));
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		// System.out.println("value hex>>" + Logger.ToHexString(value.getBytes("UTF-8")));
		// System.out.println("value hex>>" + Logger.ToHexString(value.getBytes()));
		// byte[] data =
		// SimpleCrypto.hexStringToByteArray("5756303030305120202035454141364434453343373831353230344630424541423041354142354138303238304533424441303231363030373131393031202020203031303635353632373137202020202053455353494F4E5F49443132333435202020202080000000000000000000");
		byte[] encrypted = cipher.doFinal(value.getBytes("utf-8"));
		System.out.println("encrypted hex>>" +ToHexString(encrypted));
		String result = Base64.encodeBase64String(encrypted);
		System.out.println(String.valueOf(encrypted) + "enc>>" + result);
		return result;

	}

	public static String DecryptString(String value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		byte[] encrypted = Base64.decodeBase64(value);
		SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/ISO7816-4Padding");

		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		String result = new String(decrypted, "UTF-8");
		return result;
	}
	
	public static String ToHexString(byte[] data) {
		return DatatypeConverter.printHexBinary(data);
	}
}