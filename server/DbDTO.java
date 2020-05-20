package server;

import javax.swing.*;


import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.io.File;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.Serializable;

enum Info_DB {
	JOIN
}



public class DbDTO implements Serializable{
	
	
	private int seatNum;
	private int seatCheck;
	private String memberId;
	private Info_DB command;
	
	
	
	
	public Info_DB getCommand() {
		return command;
	}
	public void setCommand(Info_DB command) {
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
