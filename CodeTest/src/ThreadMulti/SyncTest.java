package ThreadMulti;


//critical section
//synchronized 두가지 기법이 있다 .
class Bank {

	private int money = 10000;
	public synchronized void saveMoney(int save) {
		int m = this.getMoney();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		setMoney(m + save);
	}
	
	public synchronized void minusMoney(int minus) {
		int m = this.getMoney();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		setMoney(m - minus);
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}

class Park extends Thread{
	
	public void run(int money) {
		System.out.println("Start save :"+money);
		SyncTest.myBank.saveMoney(money);
		System.out.println("save money : " +SyncTest.myBank.getMoney());
	}
	
}

class ParkWife extends Thread{
	public void run(int money) {
		System.out.println("Start minus :"+ money);
		SyncTest.myBank.minusMoney(money);
		System.out.println("minus money : " +SyncTest.myBank.getMoney());
	}
}

public class SyncTest {
	
	public static Bank myBank = new Bank();
	
	public static void main(String[] args) throws InterruptedException {
		int pmoney = 3000;
		int pwmoney = 1000;
		Park p = new Park();
		p.run(pmoney);
		
		Thread.sleep(200);
		
		ParkWife pw = new ParkWife();
		pw.run(pwmoney);
		
	}
	
}

