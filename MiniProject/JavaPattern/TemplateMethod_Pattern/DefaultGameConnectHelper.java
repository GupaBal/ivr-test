package TemplateMethod_Pattern;

public class DefaultGameConnectHelper extends AbstGameConnectHelper {

	@Override
	protected String doSecurity(String stirng) {
		
		System.out.println("업무시간이 변경된 강화된 디코드 작업");
//		System.out.println("디코드 작업");
		return stirng;
	}

	@Override
	protected boolean authentication(String id, String password) {
		System.out.println("아이디/패스워드 확인");
		return true;
	}

	@Override
	protected int authorization(String userName) {
		System.out.println("권한 확인");
		return 0;
	}

	@Override
	protected String connection(String info) {
		System.out.println("마지막 접속 단계!");
		return info;
	}

}
