package modernwave_Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modernwave_CustomerInfo.CustomerInfo;
import modernwave_Http_Service.HttpRequest;

public class dataParsing {

	private Properties p;
	public dataParsing(Properties p) {
		this.p = p;
	}

	public ArrayList<String> customerDataParsiongModule(String recvData) {

		System.out.println("[dataParsing] Log.info customerDataParsiongModule() start!!");
		ArrayList<String> results = new ArrayList<String>();

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateTime = format.format(date);

		results.add(p.getProperty("BuyerCode"));
		results.add(p.getProperty("ArsRecvTel"));
		results.add(dateTime);
		results.add("NICE");
		return results;
	}

	public Map<String, String> orderDataParsiongModule(String recvData) {
		System.out.println("[dataParsing] Log.info orderDataParsiongModule() start!!");

		Map<String, String> param = new HashMap<>();
		CustomerInfo custinfo = new CustomerInfo();
		ArrayList<String> results = new ArrayList<String>();
		System.out.println("[Chat_Server_Reciever] Log.info Chat_Client Request custinfo Data List!!! ");
		String[] strArry2 = recvData.split("&");
		String[] strArry1 = recvData.split("&");
		for (int i = 0; i < strArry2.length; i++) {
			String tempStr = strArry2[i];
			int start = tempStr.indexOf("=");
			int end = tempStr.length();
			if (start > 0 && start != start + 1) {
				String tempStr1 = tempStr.substring(start + 1, end);
				String tempStr2 = tempStr.substring(0, start);
				strArry1[i] = tempStr2;
				strArry2[i] = tempStr1;
				results.add(strArry2[i]);
				param.put(strArry1[i], strArry2[i]);
			}

		}
		int i = 0;
		System.out.println("---------------------------------------------------------------------");
		param.forEach((key, value) -> {
			System.out.println("key: " + key + ", value: " + value);
			String mapKey = key;
			String mapValue = value;
			switch (mapKey) {
			case "MID":
				custinfo.setMID(mapValue);
				break;
			case "Moid":
				custinfo.setMoid(mapValue);
				break;
			case "CardQuota":
				custinfo.setCardQuota(mapValue);
				break;
			case "BuyerName":
				custinfo.setBuyerName(mapValue);
				break;
			case "ExpDate":
				custinfo.setExpDate(mapValue);
				break;
			case "AuthNum":
				custinfo.setAuthNum(mapValue);
				break;
			case "CardPwd":
				custinfo.setCardPwd(mapValue);
				break;
			case "OrderDate":
				custinfo.setOrderDate(mapValue);
				break;
			case "ResultMessage":
				custinfo.setResultMessage(mapValue);
				break;
			case "CardNum":
				custinfo.setCardNum(mapValue);
				break;
			case "StartTime":
				custinfo.setStartTime(mapValue);
				break;
			case "EndTime":
				custinfo.setEndTime(mapValue);
				break;
			case "testFlag":
				custinfo.setTestFlag(mapValue);
			case "CallCount":
				custinfo.setCallCount(mapValue);
			case "ResultCode":
				custinfo.setResultCode(mapValue);
				break;
			}
		});
		System.out.println("---------------------------------------------------------------------");
		System.out.println("*****************************************************************************");
		System.out.println("[Chat_Server_Reciever] Log.info CustomerInfo data print !!!");
		custinfo.printString();
		System.out.println("*****************************************************************************");
		return param;

	}
}
