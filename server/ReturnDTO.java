package server;


import java.io.Serializable;

enum Infom {
	JOIN
}

public class ReturnDTO implements Serializable{
	
	private int returnCheck;
	private Infom command;

	public Infom getCommand() {
		return command;
	}

	public void setCommand(Infom command) {
		this.command = command;
	}

	public int getReturnCheck() {
		return returnCheck;
	}

	public void setReturnCheck(int returnCheck) {
		this.returnCheck = returnCheck;
	}

}
