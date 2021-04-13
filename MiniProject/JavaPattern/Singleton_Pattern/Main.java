package Singleton_Pattern;

public class Main {

	public static void main(String[] args) {

		SystemSpeaker speaker1 = SystemSpeaker.getInstance();
		SystemSpeaker speaker2 = SystemSpeaker.getInstance();
		
		System.out.println(speaker1.getVoluem());
		System.out.println(speaker2.getVoluem());

		speaker1.setVoluem(11);
		System.out.println(speaker1.getVoluem());
		System.out.println(speaker2.getVoluem());
		
		speaker1.setVoluem(22);
		System.out.println(speaker1.getVoluem());
		System.out.println(speaker2.getVoluem());
	}

}
