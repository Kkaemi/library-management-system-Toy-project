package server;


//import java.io.Serializable;
//
//enum Info {
//	JOIN, EXIT, SEND
//}
//
//class InfoDTO implements Serializable{
//	private String nickName;
//	private String message;
//	private Info command;
//
//	public String getNickName(){
//		return nickName;
//	}
//	public String getMessage(){
//		return message;
//	}
//	public Info getCommand(){
//		return command;
//	}
//
//	public void setNickName(String nickName){
//		this.nickName = nickName;
//	}
//	public void setMessage(String message){
//		this.message = message;
//	}
//	public void setCommand(Info command){
//		this.command = command;
//	}
//}




import java.io.Serializable;


enum Info {
	JOIN, EXIT
}


public class InfoDTO implements Serializable{
	
	
	private int seatNum;
	private int seatCheck;
	private String memberId;

	private Info command;


	

	
	
	
	public Info getCommand() {
		return command;
	}
	public void setCommand(Info command) {
		this.command = command;
	}
	
	

	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public int getSeatCheck() {
		return seatCheck;
	}
	public void setSeatCheck(int seatCheck) {
		this.seatCheck = seatCheck;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	

}

















