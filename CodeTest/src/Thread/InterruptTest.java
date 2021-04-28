package Thread;

public class InterruptTest extends Thread{

	public void run() {
		int i;
		for (i = 0; i < 100;i++) {
			System.out.println(i);
		}
		
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			System.out.println(e);
			System.out.println("Wake!!!");
		}
	}
	
	public static void main(String[] args) {

		InterruptTest test = new InterruptTest();
		test.start();
		test.interrupt();
		
		//sleep중일때 InterrupteExcption으로 깨어나게 한다. test.interrupt();을 호출하면 5초 딜레이 없이 종료됨
		
		System.out.println("end");
	}

}
