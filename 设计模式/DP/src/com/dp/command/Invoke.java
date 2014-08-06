package com.dp.command;

public class Invoke {
	private Command command;
	public Invoke(Command command){
		this.command = command;
	}
	
	public void action(){
		command.exec();
	}
}
