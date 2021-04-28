package test;

import java.io.PrintStream;

public class asdasd {

	public static class PrintStreamDemo {
		   public static void main(String[] args) {
		      String s = "Hello World.";

		      // create printstream object
		      PrintStream ps = new PrintStream(System.out);

		      // print our string
		      ps.print(s);

		      // flush the stream to see the results
		      ps.flush();
		      
		      ps.print(s);

		      // print new string
		      ps.println();
		      ps.print("This is an example");

		      // flush to see new string
		      ps.flush();
		   }
		}

}
