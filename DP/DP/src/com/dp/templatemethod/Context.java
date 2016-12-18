package com.dp.templatemethod;

public class Context extends Container {
	public Context(String name){
		this.name = name;
	}
	public void startInternal(){
		System.out.println("start Context++++++++++++++++++++ " + name);
	}
}
