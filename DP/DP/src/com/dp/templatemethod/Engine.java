package com.dp.templatemethod;

import java.util.ArrayList;
import java.util.List;

public class Engine extends Container {
	private List<Host> hosts = new ArrayList<Host>();
	public Engine(String name){
		this.name = name;
	}
	public void startInternal(){
		System.out.println("begin start Engine----" + name);
		for(Host host:hosts){
			host.start();
		}
		//System.out.println("after start Engine----" + name);
	}
	public void add(Container host){
		hosts.add((Host)host);
	}
}
