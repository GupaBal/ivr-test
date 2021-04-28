package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.swing.text.html.HTMLEditorKit.Parser;

public class time {

	public static void main(String[] args) {

		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		
		

		Date time = new Date();

		String time1 = "2021-03-09 13:55:40";
		String dateToStr1 = format1.format(time);
		try {
			Date d1 = format1.parse(dateToStr1);
			Date d2 = format2.parse(time1);
			System.out.println("d1= "  +d1);
			System.out.println("d2= "  +d2);
			System.out.print("d1 - d2 ");
			System.out.println(d1.getTime() - d2.getTime());
			long diff = d2.getTime() - d1.getTime();
			long sec = diff / 1000;
			System.out.println("sec = "+sec);
			int hour = (int) (sec / 60);
			int minute = (int) (sec / 60);
			int second = (int) (sec % 60);
			
			if(sec >=  3600) {
				hour = (int) (sec / 3600) ;
				minute = (int) ((sec - 3600*hour) / 60);
				second = (int) (sec % 60);
				System.out.println("(int) (sec / 3600) "+hour+"시");
				System.out.println("(int) (sec / 60)"+minute+"분");
				System.out.println("(int) (sec % 60)"+second+"초");
			
			}else {
				minute = (int) (sec / 60);
				second = (int) (sec % 60);
				System.out.println("(int) (sec / 60)"+minute+"분");
				System.out.println("(int) (sec % 60)"+second+"초");
			}

			
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		
		
		
//		String time1 = format1.format(time);
//		String time2 = format2.format(time);
//				
//		int temp = Integer.parseInt(time1) - Integer.parseInt(time2) ;
//		
//		System.out.println(time1);
//		System.out.println(time2);
//		System.out.println(temp);
//		
	}

}
