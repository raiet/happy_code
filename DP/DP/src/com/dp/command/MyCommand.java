package com.dp.command;

public class MyCommand implements Command {
	private Reciver reciver ;
	public MyCommand(Reciver reciver){
		this.reciver= reciver;
	}
	@Override
	public void exec() {
		reciver.action();
	}

}
