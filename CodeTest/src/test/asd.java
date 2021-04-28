package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class asd {

	public static void main(String[] args) {

		try {

			FileInputStream fis = new FileInputStream("src/test/asd.java");

			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

// ///////////////////////////////////////////////////////////////////////////////////

			FileOutputStream fos = new FileOutputStream("LineNum.txt");

			PrintStream ps = new PrintStream(fos);

// ///////////////////////////////////////////////////////////////////////////////////

			int num = 1;

			String buf;

			while (true) {

				buf = br.readLine();

				if (buf == null) {

					break;

				}

				buf = num + " : " + buf;

				ps.println(buf);

				num++;

			}

			System.out.println("Success");

			ps.close();

			fos.close();

			br.close();

			fis.close();

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

}