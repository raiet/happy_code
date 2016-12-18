package com.dp.templatemethod;

public abstract class Container {
	protected String name;
	public void start(){
		//System.out.println("before container start");
		startInternal();
		//System.out.println("after container start");
	}
	
	public void startInternal(){}
	public void add(Container c){}
}
