package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class bonsa {

	public static void main(String[] args) {

		String cust = "id";
		String type1 = "CHAN0002";
		String type2 = "CHAN0002";
		// 20200805 1. ��ȿ�� ������� �� CHANNEL_TYPE_CD ����
		/////////// CHAN0001, CHAN0003 ���� �� ���̶� �����̸� ����� (YES)
		boolean isBonsa = false;
		if(cust != null && type1.equalsIgnoreCase("CHAN0002")) {
			if (!isBonsa) {
				isBonsa = false;
				System.out.println("	isBonsa = false;");
			} else {
			}
		} else {
			isBonsa = true;
			System.out.println("	isBonsa = true;");
		}
		
		if(cust != null && type2.equalsIgnoreCase("CHAN0002")) {
			if (!isBonsa) {
				isBonsa = false;
				System.out.println("	isBonsa = false;");
			} else {
			}
		} else {
			isBonsa = true;
			System.out.println("	isBonsa = true;");
		}
		
		String nowDateValuestr = "20210225";
		String endDateValue1str = "20151231";
		String endDateValue2str = "20211231";
		double nowDateValue = Double.parseDouble(nowDateValuestr);
		double endDateValue1 = Double.parseDouble(endDateValue1str);
		double endDateValue2 = Double.parseDouble(endDateValue2str);
		
		
		if (nowDateValue <= endDateValue1) {
			if (isBonsa) {
				System.out.println("MODULE: BLUEMEMBERSHIP ������� �Ⱓ ���̰� ����� Ȯ�ε�. From Douzone Service"+endDateValue1str);
			} else {
				System.out.println("MODULE: BLUEMEMBERSHIP ������� �Ⱓ �������� ���簡 �ƴ�. From Douzone Service"+endDateValue1str);
			}
			
		}
		
		if (nowDateValue <= endDateValue2) {
			
			if (isBonsa) {
				System.out.println("MODULE: BLUEMEMBERSHIP ������� �Ⱓ ���̰� ����� Ȯ�ε�. From Douzone Service"+endDateValue2str);
			} else {
				System.out.println("MODULE: BLUEMEMBERSHIP ������� �Ⱓ �������� ���簡 �ƴ�. From Douzone Service"+endDateValue2str);
			}
		}
		
	}

}
