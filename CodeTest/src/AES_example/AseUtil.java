package AES_example;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AseUtil {


	public static String key = "0927454521097845";
	

	/**
	 * hex to byte[] : 16진수 문자열을 바이트 배열로 변환한다.
	 *
	 * @param hex
	 *  hex string
	 * @return
	 */
	
	public static byte[] hexToByteArray(String hex) {
		if(hex == null || hex.length() == 0) {
			return null;
		}
		
		byte[] ba = new byte[hex.length()/2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		
		System.out.println("(7) hexToByteArrayba  = "+ba);
		return ba;
	}
	
	
	/**
	 * byte[] to hex : unsigned byte(바이트) 배열을 16진수 문자열로 바꾼다.
	 *
	 * @param ba
	 *            byte[]
	 * @return
	 */
	
	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}

		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		System.out.println("(2) byteArrayToHex sb.toString() "+sb.toString());
		return sb.toString();
	}
	
	
	/**
	 * AES 방식의 암호화
	 *
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String message) throws Exception {
		// KeyGenerator kgen = KeyGenerator.getInstance("AES");
		// kgen.init(128);
		// use key coss2
		
		System.out.println("(1)encrypt message =" +message);
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
		System.out.println("(2)encrypt SecretKeySpec  skeySpec=" +skeySpec);
		// Instantiate the cipher
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		byte[] encrypted = cipher.doFinal(message.getBytes());
		System.out.println("(3)encrypt SecretKeySpec  message.getBytes()=" +message.getBytes());
		return byteArrayToHex(encrypted);
	}
		/**
		 * AES 방식의 복호화
		 *
		 * @param message
		 * @return
		 * @throws Exception
		 */
		public static String decrypt(String encrypted) throws Exception {
			// KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// kgen.init(128);
			// use key coss2
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
			System.out.println("(8) decrypt encrypted = "+ encrypted);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] original = cipher.doFinal(hexToByteArray(encrypted));
			return new String(original);
		}
		
		public static void main(String[] args) throws Exception {
			AseUtil au = new AseUtil();
			String asd = au.encrypt("dka");
			System.out.println("(5) String asd = "+asd);
			String adb = "";
			adb = au.decrypt(asd);
			System.out.println(adb);
		}
}
