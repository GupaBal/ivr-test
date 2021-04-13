package Singleton_Pattern;

public class SystemSpeaker {

	//하나만 있어야 하기 때문에 static으로 선언 
	static private SystemSpeaker instance;
	private int volume = 5;
	

	private SystemSpeaker() {
		volume = 5;
	}
	
	public static SystemSpeaker getInstance() {
		if(instance == null) {
			//시스템 스피커 
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
