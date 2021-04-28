package test;

public class Synchronization {

	private String mMessage;
	private String mHero;

	public static void main(String[] args) {

		Synchronization temp = new Synchronization();

		System.out.println("Test Start!!");

		new Thread(() -> {
			for (int i = 0; i < 10000000; i++) {
				temp.batman();
			}
		}).start();

		new Thread(() -> {
			for (int i = 0; i < 10000000; i++) {
				temp.superman();
			}
		}).start();

		System.out.println("Test end!");

	}

	private void batman() {
		mHero = "batman";

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
			if ("batman".equals(mHero) == false) {
				System.out.println("synchronization broken");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

	private synchronized void superman() {
		mHero = "superman";

		try {
			long sleep = (long) (Math.random() * 100);
			Thread.sleep(sleep);
			if ("superman".equals(mHero) == false) {
				System.out.println("synchronization broken");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}


}
