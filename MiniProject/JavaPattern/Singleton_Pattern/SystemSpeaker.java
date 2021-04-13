package Singleton_Pattern;

public class SystemSpeaker {

	//�ϳ��� �־�� �ϱ� ������ static���� ���� 
	static private SystemSpeaker instance;
	private int volume = 5;
	

	private SystemSpeaker() {
		volume = 5;
	}
	
	public static SystemSpeaker getInstance() {
		if(instance == null) {
			//�ý��� ����Ŀ 
			instance = new SystemSpeaker();
		}
		return instance;
	}
	
	public int getVoluem() {
		return volume;
	}
	
	public void setVoluem(int volume) {
		this.volume = volume;
	}
}
