package test;

import java.io.UnsupportedEncodingException;

public class euckr {

	public static void main(String[] args) throws UnsupportedEncodingException {

		String d = "¾È³çº´ÁØ¾Æ";
		
		byte[] data = null;
		byte[] data2 = null;
		data = d.getBytes("euc-kr");
		data2 = d.getBytes("UTF8");
		
		System.out.println("data"+data);
		System.out.println("data.length"+data.length);
		System.out.println((new String(data)));

		
		System.out.println("data2"+data2);
		System.out.println("data2.length"+data2.length);
		System.out.println((new String(data2)));
	}

}
