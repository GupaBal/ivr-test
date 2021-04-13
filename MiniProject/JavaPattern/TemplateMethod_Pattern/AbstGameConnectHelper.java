package TemplateMethod_Pattern;

public abstract class AbstGameConnectHelper {
	
	//외부에서는 호출이 되면 안되는데 하위 클래스에서는 사용되어야 함. protected로 
	protected abstract String doSecurity(String stirng);
	protected abstract boolean authentication(String id,String password);
	protected abstract int authorization(String userName);
	protected abstract String connection(String info);
	
	// 탬플릿 메소드 (접속을 도와줌)
	public String requestConnection(String encodedInfo) {
		
		//보안 작업 -> 암호화 된 문자열을 복호화 해줌.
		String decodedInfo = doSecurity(encodedInfo);
		
		//반환된 것을 가지고, 아이디,패스워드를 할당한다. 
		String id = "modern";
		String password = "1234";
		
		
		//인증 과정 -> 반한된 아이디와 패스워드로 인증
		if(!authentication(id, password)) {
			System.out.println("아이디 및 암호 불일치");
			throw new Error("아이디 및 암호 불일치");
		}
		
		//권한 과정 ->
		String userName = "joshua";
		int i = authorization(userName);
		
		switch(i) {
		case 0: //게임 매니저
			System.out.println("게임 매니저");
			break;
		case 1: //유료회원
			System.out.println("유료회원");
			break;
		case 2: //무료회원
			System.out.println("무료회원");
			break;
		case 4: //권환없음
			System.out.println("권환없음");
			break;
		default: // 기타사항
			System.out.println("기타사항");
			break;
		}
		
		return connection(decodedInfo);
	}
	
}
