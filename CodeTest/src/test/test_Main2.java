package test;

import java.io.IOException;
import java.lang.management.ManagementFactory;

public class test_Main2 {
	
	public static String process_pid() {
		  String name = ManagementFactory.getRuntimeMXBean().getName();
		  String pidNumber = name.substring(0, name.indexOf("@"));
		  String[] split = name.split("@");
		  System.out.println("name = "+name);
		  System.out.println("pidNumber = "+pidNumber);
		  System.out.println("split[0] = "+split[0]);
		  System.out.println("split[1] = "+split[1]);
		  if (split.length != 2) {
		    throw new RuntimeException("Got unexpected process name: " + name);
		  }
		  System.out.println("split[0]"+split[0]);
		  return split[0];
		}
	
	
	public static void main(String[] args) {


		process_pid();
		
	}

}
