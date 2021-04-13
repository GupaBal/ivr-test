package modernwave_Http_Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import modernwave_CustomerInfo.CustomerInfo;

public class HttpRequest {

	private HTTP_Post post;

	public HttpRequest(HTTP_Post post) {
		this.post = post;
	}

	public void CheckArsOrder_Customer(ArrayList<String> List) {
		System.out.println("[HttpRequest] Log.info CheckArsOrder_Customer() start!!");
		Map<String, String> param = new HashMap<>();
		Map<String, Map<String, String>> result = null;

		if (List.size() == 0 || List.isEmpty()) {
			System.out.println("[HttpRequest] Log.info results.size()== null");
		}

		param.put("BuyerCode", List.get(0));
		param.put("ArsRecvTel", List.get(1));
		param.put("RecvDate", List.get(2));
		param.put("ReqArsFirm", List.get(3));
		System.out.println("[HttpRequest] Log.info  CheckArsOrder_Customer List BuyerCode : " + List.get(0)
				+ " arsRecvTel :" + List.get(1) + " RecvDate :" + List.get(2) + " ReqArsFirm :" + List.get(3));
		CustomerInfo custInfo = new CustomerInfo();
		result = onRequestOrder_Customer_List(param);

		
		custInfo.setBuyerCode(List.get(0)); // results.add("BuyerCode");
		custInfo.setArsRecvTel(List.get(1)); // results.add("ArsRecvTel");
		custInfo.setRecvDate(List.get(2)); // results.add(dateTime);
		custInfo.setReqArsFirm(List.get(3)); // results.add("NICE");
		custInfo.printStringCustInfo();
	}

	public Map<String, Map<String, String>> onRequestOrder_Customer_List(Map<String, String> param) {
		Map<String, Map<String, String>> result = null;
		try {
			result = post.onRequestCustomer_Confirm(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result.size() == 0) {
			System.out.println("[HTTP_Post] Log.info HTTP_Post result.size()==0 ");
			return result;
		}

		System.out.println(
				"------------------------------------------------------------------------------------------------------------");
		result.forEach((key, value) -> {
			System.out.println("1 : key: " + key + ", value: " + value);
			value.forEach((key2, value2) -> {
				System.out.println("2 : key: " + key2 + ", value: " + value2);
			});
		});

		System.out.println(
				"------------------------------------------------------------------------------------------------------------");

		return result;

	}

	public Map<String, Map<String, String>> CheckArsOrder_Payment(Map<String, String> param) {

		Map<String, Map<String, String>> result = null;

		try {
			result = post.onRequestOrder_Payment(param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

}
