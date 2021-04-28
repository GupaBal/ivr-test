package Thread;

import java.io.IOException;

public class TerminateThread extends Thread{

	private boolean flag = false;
	int i;
	
	public TerminateThread(String name) {
		super(name);
	}
	
	public void run() {
		while(!flag) {
			
			try {
				sleep(100);
				System.out.println(getName()+"move!move!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(getName()+"end");
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public static void main(String[] args) throws IOException {

		TerminateThread threadA = new TerminateThread("A");
		TerminateThread threadB = new TerminateThread("B");
		
		threadA.start();
		threadB.start();
		
		//flag를 이용하여 
		
		int in;
		while(true) {
			in = System.in.read();
			if(in == 'A') {
				threadA.setFlag(true);
			}else if(in == 'B') {
				threadB.setFlag(true);
			}else if(in == 'M') {
				threadA.setFlag(true);
				threadB.setFlag(true);
				break;
			}
		}
		
		System.out.println("main end");
	}

}
