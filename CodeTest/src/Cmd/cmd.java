package Cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cmd {

	private StringBuffer buffer;
	private Process process;
	private BufferedReader bufferReader;
	private StringBuffer readBuffer;
	
	public String inputCommand(String cmd) {
		buffer = new StringBuffer();
		
		buffer.append("cmd.exe ");
		buffer.append("/c");
		buffer.append(cmd);
		
		return buffer.toString();
	}
	
	public String execCommand(String cmd) throws IOException {
		cmd = "notepad.exe";		
		process = Runtime.getRuntime().exec(cmd);
		bufferReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String line = null;
		readBuffer = new StringBuffer();
		
		while((line = bufferReader.readLine())!=null) {
			readBuffer.append(line);
			readBuffer.append("\n");
		}
		return readBuffer.toString();
		
	}
	
	public static void main(String[] args) throws IOException {

		cmd cmd = new cmd();
		
		String command = cmd.inputCommand("ipconfig");
		String result = cmd.execCommand(command);
		
		System.out.println(result);
	}

}
